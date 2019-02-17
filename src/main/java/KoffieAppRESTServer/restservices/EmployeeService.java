package KoffieAppRESTServer.restservices;

import KoffieAppRESTServer.handlers.IEmployeeHandler;
import KoffieAppRESTServer.response.Reply;
import com.google.gson.Gson;
import models.Employee;
import utils.GsonUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/koffieapp/employee")
public class EmployeeService {
    private static IEmployeeHandler handler;

    public static void setHandler(IEmployeeHandler handler) {
        EmployeeService.handler = handler;
    }

    @GET
    @Path("/all")
    public Response getEmployees() {
        Reply reply = handler.getEmployees();

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployee(@PathParam("id") int employeeId) {
        Reply reply = handler.getEmployee(employeeId);

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveEmployee(String data) {
        Employee employee = GsonUtils.fromJson(data, Employee.class);

        Reply reply = handler.saveEmployee(employee);

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @PUT
    @Path("/edit")
    @Consumes("application/json")
    public Response editEmployee(String data) {
        Employee employee = GsonUtils.fromJson(data, Employee.class);

        Reply reply = handler.saveEmployee(employee);

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
