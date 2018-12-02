package KoffieAppRESTServer.handlers;

import models.Drink;
import models.Employee;
import models.Order;
import KoffieAppRESTServer.response.ErrorJson;
import KoffieAppRESTServer.response.OrderJson;
import KoffieAppRESTServer.response.Reply;
import KoffieAppRESTServer.response.Status;
import com.google.gson.Gson;
import KoffieAppDal.repository.OrderRepository;
import logging.Logger;

import java.util.ArrayList;
import java.util.List;


public class OrderHandler implements IOrderHandler{
    private OrderRepository orderRepository;
    private Gson gson;

    public OrderHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.gson = new Gson();
    }

    @Override
    public Reply getOrders() {
        try {
            addOrders();
            List<OrderJson> orderResponse = new ArrayList<>();

            for (Order o : orderRepository.findAll()) {

                orderResponse.add(new OrderJson(o.getId(), o.getEmployee(), o.getDrink(), o.getSugarAmount(), o.getMilkAmount()));
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
        Order order = orderRepository.findOne(orderId);

        if (order != null) {
            String json = gson.toJson(order);

            return new Reply(Status.OK, json);
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply saveOrder(Order order) {
        Order saved = orderRepository.save(order);

        if (saved.getId() == order.getId()) {
            return new Reply(Status.OK, gson.toJson(saved));
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply deleteOrder(int orderId) {
        try {
            orderRepository.delete(orderId);

            return new Reply(Status.OK, gson.toJson(orderId));
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");

            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    private void addOrders() {
        Employee employee1 = new Employee(1, "Henk", "Ruijter");
        Employee employee2 = new Employee(2, "Sanne", "Pell");

        Drink drink1 = new Drink(1, "Koffie");
        Drink drink2 = new Drink(2, "Thee");
        Drink drink3 = new Drink(3,"Cappuccino");
        Drink drink4 = new Drink(4,"Latte");

        Order order1 = new Order(employee1, drink1, 2, 1);
        Order order2 = new Order(employee1, drink3, 1, 0);
        Order order3 = new Order(employee2, drink2, 1, 3);
        Order order4 = new Order(employee2, drink4, 1, 1);

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
    }
}
