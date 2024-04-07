package org.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    //MY IP: 46.18.203.145
    public static void main(String[] args) throws Exception {

        AllRequestsServlet allRequestServlet = new AllRequestsServlet();
        AuthorizationServlet authorizationServlet = new AuthorizationServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestServlet),"/home/*");

        context.addServlet(new ServletHolder(authorizationServlet),"/authorization/*");

        Server server = new Server(8080);

        server.setHandler(context);
        server.start();
        server.join();
    }
}
