package org.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.data_base.DBService;
import org.pages.PageLoader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationServlet extends HttpServlet {

    DBService data_base;

    public AuthorizationServlet() {
        try {
            this.data_base = new DBService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageLoader.instance().getPage("temp/singin.html"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            pageVariables.replace("massage", "those fields are required to be filled in");
        } else /*if (validation(login, password))*/ {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        response.setContentType("text/html;utf-8");
        response.getWriter().println(PageLoader.instance().getPage("temp/singin.html", pageVariables));
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
    private boolean validation(String login, String password) {
        return password==
    }
}
