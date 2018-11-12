package KoffieRESTServer.handlers;

import KoffieRESTServer.response.Reply;

public interface IOrderHandler {
    Reply getOrders();
    Reply saveOrder();
    Reply editOrder();
}
