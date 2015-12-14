package com.denm.server.servlet;

import com.denm.server.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DENM on 14.12.2015.
 */
public class AllRequestServlet extends HttpServlet {
    private final static String PAGE_NAME = "page.html";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("message", "");

        response.getWriter().println(PageGenerator.instance().getPage(PAGE_NAME, pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String message = request.getParameter("message");

        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        pageVariables.put("message", message == null ? "" : message);
        response.getWriter().println(PageGenerator.instance().getPage(PAGE_NAME, pageVariables));
    }


    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());

        return pageVariables;
    }

}