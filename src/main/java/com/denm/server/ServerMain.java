package com.denm.server;

import com.denm.server.servlet.AllRequestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.logging.Logger;

/**
 * Created by DENM on 14.12.2015.
 */
public class ServerMain {
    private final static Logger logger = Logger.getGlobal();

    public static void main(String[] args) throws Exception {
        logger.info("Server started");
        AllRequestServlet allRequestsServlet = new AllRequestServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/mirror/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
