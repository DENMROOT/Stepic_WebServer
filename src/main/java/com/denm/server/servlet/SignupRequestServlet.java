package com.denm.server.servlet;

import com.denm.server.exception.DBException;
import com.denm.server.service.AuthenticationService;
import com.denm.server.service.AuthenticationServiceImpl;
import com.denm.server.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by DENM on 14.12.2015.
 */
public class SignupRequestServlet extends HttpServlet {
    private final static String PAGE_NAME = "page.html";
    private final Logger logger = Logger.getGlobal();
    private final AuthenticationService authService = AuthenticationServiceImpl.getInstance();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType("text/html;charset=utf-8");
        logger.info("Store credentials request for: login=" +login + ", password=" + password);

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            try {
                authService.storeCredentials(login, password);
            } catch (DBException e) {
                logger.info("Unexpected DB error: "  + e.getMessage());
            }
        }

        Map<String, Object> pageVariables = createPageVariablesMap(request);

        pageVariables.put("message", login + ", " + password + " stored");
        response.getWriter().println(PageGenerator.instance().getPage(PAGE_NAME, pageVariables));
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();

        return pageVariables;
    }
}