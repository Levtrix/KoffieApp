package KoffieAppRESTServer.handlers;

import KoffieAppRESTServer.models.Drink;
import KoffieAppRESTServer.models.Employee;
import KoffieAppRESTServer.models.Order;
import KoffieAppRESTServer.response.ErrorJson;
import KoffieAppRESTServer.response.OrderJson;
import KoffieAppRESTServer.response.Reply;
import KoffieAppRESTServer.response.Status;
import com.google.gson.Gson;
import KoffieAppDatabase.dal.repository.DrinkRepository;
import KoffieAppDatabase.dal.repository.EmployeeRepository;
import KoffieAppDatabase.dal.repository.OrderRepository;
import KoffieAppDatabase.logging.Logger;
import KoffieAppDatabase.models.*;

import java.util.ArrayList;
import java.util.List;


public class OrderHandler implements IOrderHandler{
    private OrderRepository orderRepository;
    private DrinkRepository drinkRepository;
    private EmployeeRepository employeeRepository;
    private Gson gson;

    public OrderHandler(OrderRepository orderRepository, DrinkRepository drinkRepository, EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.gson = new Gson();
        this.drinkRepository = drinkRepository;
        this.employeeRepository = employeeRepository;
    }

    // TODO: Add better error handling
    @Override
    public Reply getOrders() {
        try {
            List<Order> orders = new ArrayList<>();
            List<OrderJson> orderResponse = new ArrayList<>();

            for (SQLOrder o : orderRepository.findAll()) {
                SQLDrink tmp = drinkRepository.findOne(o.getDrinkId());
                Drink drink = new Drink(tmp.getDrinkId(), tmp.getName());
                SQLEmployee tmp1 = employeeRepository.findOne(o.getEmployeeId());
                Employee employee = new Employee(tmp1.getEmployeeId(), tmp1.getFirstName(), tmp1.getLastName());

                orders.add(new Order(o.getDrinkId(), employee, drink, o.getSugarAmount(), o.getMilkAmount()));
            }

            for (Order o : orders) {
                orderResponse.add(new OrderJson(o.getOrderId(), o.getEmployee(), o.getDrink(), o.getSugarAmount(), o.getMilkAmount()));
            }

            String json = gson.toJson(orderResponse);

            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");

            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    @Override
    public Reply getOrder(int orderId) {
        if (orderId != 0) {
            try {
                SQLOrder o = orderRepository.findOne(orderId);
                SQLDrink tmp = drinkRepository.findOne(o.getDrinkId());
                Drink drink = new Drink(tmp.getDrinkId(), tmp.getName());
                SQLEmployee tmp1 = employeeRepository.findOne(o.getEmployeeId());
                Employee employee = new Employee(tmp1.getEmployeeId(), tmp1.getFirstName(), tmp1.getLastName());

                Order order = new Order(o.getDrinkId(), employee, drink, o.getSugarAmount(), o.getMilkAmount());

                String json = gson.toJson(new OrderJson(order.getOrderId(), order.getEmployee(), order.getDrink(), order.getSugarAmount(), order.getMilkAmount()));

                return new Reply(Status.OK, json);
            } catch (Exception e) {
                Logger.getInstance().log(e);
                ErrorJson errorJson = new ErrorJson("Something went wrong");

                return new Reply(Status.ERROR, gson.toJson(errorJson));
            }
        }
        else {
            ErrorJson errorJson = new ErrorJson("Something went wrong");

            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    @Override
    public Reply saveOrder(Order order) {
        SQLOrder saved = orderRepository.save(new SQLOrder(order.getOrderId(), order.getEmployee().getEmployeeId(), order.getDrink().getDrinkId(), order.getSugarAmount(), order.getMilkAmount()));

        if (saved.getOrderId() == order.getOrderId()) {
            SQLDrink tmp = drinkRepository.findOne(saved.getDrinkId());
            Drink drink = new Drink(tmp.getDrinkId(), tmp.getName());
            SQLEmployee tmp1 = employeeRepository.findOne(saved.getEmployeeId());
            Employee employee = new Employee(tmp1.getEmployeeId(), tmp1.getFirstName(), tmp1.getLastName());

            Order order1 = new Order(saved.getOrderId(), employee, drink, saved.getSugarAmount(), saved.getMilkAmount());

            return new Reply(Status.OK, gson.toJson(order1));
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    //TODO: Check if this is the correct way of handling this
    @Override public Reply deleteOrder(int orderId) {
        try {
            orderRepository.delete(orderId);

            return new Reply(Status.OK, gson.toJson(orderId));
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");

            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }
}
