package KoffieAppRESTServer.restservices;

import KoffieAppRESTServer.handlers.IOrderHandler;
import KoffieAppRESTServer.response.Reply;
import com.google.gson.Gson;
import models.Order;
import utils.GsonUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/koffieapp/order")
public class OrderService {
    private static IOrderHandler handler;

    public static void setHandler(IOrderHandler handler) {
        OrderService.handler = handler;
    }

    @GET
    @Path("/all")
    public Response getOrders() {
        Reply reply = handler.getOrders();

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") int orderId) {
        Reply reply = handler.getOrder(orderId);

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveOrder(String data) {
        Order order = GsonUtils.fromJson(data, Order.class);

        Reply reply = handler.saveOrder(order);

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @PUT
    @Path("/edit")
    @Consumes("application/json")
    public Response editOrder(String data) {
        Order order = GsonUtils.fromJson(data, Order.class);

        Reply reply = handler.saveOrder(order);

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteOrder(@PathParam("id") int orderId) {
        Reply reply = handler.deleteOrder(orderId);

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
