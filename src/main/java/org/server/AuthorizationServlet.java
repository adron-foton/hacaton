package org.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.pages.PageLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationServlet extends HttpServlet {

    private String login = "";
    private String password = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageLoader.instance().getPage("singin.html"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            pageVariables.replace("massage", "those fields are required to be filled in");
        } else /*if (authorize(login, password))*/ {
            this.login = login;
            this.password = password;
            response.setStatus(HttpServletResponse.SC_OK);
        }

        response.setContentType("text/html;utf-8");
        response.getWriter().println(PageLoader.instance().getPage("singin.html", pageVariables));
        System.out.println(PageLoader.instance().getPage("singin.html", pageVariables).toString());
    }

//    private boolean authorize(String login, String password) {
//
//    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", " ");
        pageVariables.put("login", request.getParameter("login"));
        pageVariables.put("password", request.getParameter("password"));

        return pageVariables;
    }
}
