package KoffieRESTServer.handlers;

import KoffieRESTServer.response.Reply;
import com.google.gson.Gson;
import dal.repository.OrderRepository;


public class OrderHandler implements IOrderHandler{
    private OrderRepository repository;
    private Gson gson;

    public OrderHandler(OrderRepository repository) {
        this.repository = repository;
        this.gson = new Gson();
    }

    @Override
    public Reply getOrders() {

        return null;
    }

    @Override
    public Reply saveOrder() {
        return null;
    }

    @Override
    public Reply editOrder() {
        return null;
    }
}
