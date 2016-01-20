package com.denm.server;

import com.denm.server.exception.DBException;
import com.denm.server.model.User;
import com.denm.server.service.DBService;
import com.denm.server.servlet.SigninRequestServlet;
import com.denm.server.servlet.SignupRequestServlet;
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
        SignupRequestServlet signupServlet = new SignupRequestServlet();
        SigninRequestServlet signinServlet = new SigninRequestServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signupServlet), "/signup");
        context.addServlet(new ServletHolder(signinServlet), "/signin");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
