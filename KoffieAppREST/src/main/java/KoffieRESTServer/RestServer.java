package KoffieRESTServer;

import KoffieRESTServer.handlers.*;
import KoffieRESTServer.restservices.DrinkService;
import KoffieRESTServer.restservices.EmployeeService;
import KoffieRESTServer.restservices.OrderService;
import dal.repository.DrinkRepository;
import dal.repository.EmployeeRepository;
import dal.repository.OrderRepository;
import logging.Logger;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.glassfish.jersey.servlet.ServletContainer;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class RestServer {

    public static void main(String[] args) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server();

        int secureport = 8097;
        int port = 8098;

        //http
        HttpConfiguration http = new HttpConfiguration();
        http.setSecureScheme("https");
        http.setSecurePort(secureport);

        //http connector
        ServerConnector httpconnector = new ServerConnector(jettyServer, new HttpConnectionFactory(http));
        httpconnector.setIdleTimeout(30000);
        httpconnector.setPort(port);

        //https config
        HttpConfiguration https = new HttpConfiguration(http);
        https.addCustomizer(new SecureRequestCustomizer());

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(RestServer.class.getResource("/keystore.jks").toExternalForm());
        sslContextFactory.setKeyStorePassword("qwerty");
        sslContextFactory.setKeyManagerPassword("qwerty");

        ServerConnector sslConnector = new ServerConnector(jettyServer,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https));
        sslConnector.setPort(secureport);

        jettyServer.setConnectors(new Connector[]{sslConnector, httpconnector});
        //region Origin header
        FilterHolder cors = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        //endregion

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

        // Tells the Jersey Servlet which REST service/class to load.
         String services = DrinkService.class.getCanonicalName() + ", " + EmployeeService.class.getCanonicalName() + ", " + OrderService.class.getCanonicalName();
         // "restservices.DrinkService, restservices.EmployeeService, restservices.OrderService"

        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", services);
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
