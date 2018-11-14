package KoffieAppRESTServer.handlers;

import KoffieAppRESTServer.response.Reply;
import KoffieAppDatabase.models.Order;

public interface IOrderHandler {
    Reply getOrders();
    Reply getOrder(int orderId);
    Reply saveOrder(Order order);
    Reply deleteOrder(int orderId);
}
