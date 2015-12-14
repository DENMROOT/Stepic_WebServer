package com.denm.server;

import com.denm.server.servlet.AllRequestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by DENM on 14.12.2015.
 */
public class ServerMain {
    public static void main(String[] args) throws Exception {
        AllRequestServlet allRequestsServlet = new AllRequestServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
