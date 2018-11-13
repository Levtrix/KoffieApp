package KoffieRESTServer.handlers;

import KoffieRESTServer.response.Reply;
import models.Order;

public interface IOrderHandler {
    Reply getOrders();
    Reply getOrder(int orderId);
    Reply saveOrder(Order order);
    Reply editOrder(Order order);
    Reply deleteOrder(int orderId);
}
