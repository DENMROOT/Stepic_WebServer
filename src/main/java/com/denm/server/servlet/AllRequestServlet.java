package com.denm.server.servlet;

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
public class AllRequestServlet extends HttpServlet {
    Logger logger = Logger.getGlobal();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String message = request.getParameter("key");

        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }


        response.getWriter().println(message);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}