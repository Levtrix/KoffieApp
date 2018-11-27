package KoffieAppRESTServer;

import KoffieAppRESTServer.handlers.*;
import KoffieAppRESTServer.restservices.DrinkService;
import KoffieAppRESTServer.restservices.EmployeeService;
import KoffieAppRESTServer.restservices.OrderService;
import KoffieAppDatabase.dal.repository.DrinkRepository;
import KoffieAppDatabase.dal.repository.EmployeeRepository;
import KoffieAppDatabase.dal.repository.OrderRepository;
import KoffieAppDatabase.logging.Logger;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class RestServer {
    private static final int PORT = 8095;

    public static void main(String[] args) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(PORT);

        // region Origin header
        FilterHolder cors = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        // endregion

        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Creating handlers
        IDrinkHandler drinkHandler = new DrinkHandler(new DrinkRepository());
        IEmployeeHandler employeeHandler = new EmployeeHandler(new EmployeeRepository());
        IOrderHandler orderHandler = new OrderHandler(new OrderRepository(), new DrinkRepository(), new EmployeeRepository());

        DrinkService.setHandler(drinkHandler);
        EmployeeService.setHandler(employeeHandler);
        OrderService.setHandler(orderHandler);

        // Tells the Jersey Servlet which REST service/class to load
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "KoffieAppRESTServer.restservices");
        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            Logger.getInstance().log(e);
        } finally {
            jettyServer.destroy();
        }
    }
}
