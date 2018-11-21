package KoffieAppRESTServer.handlers;

import KoffieAppRESTServer.response.Reply;
import KoffieAppRESTServer.models.Order;

public interface IOrderHandler {
    Reply getOrders();
    Reply getOrder(int orderId);
    Reply saveOrder(Order order);
    Reply deleteOrder(int orderId);
}
