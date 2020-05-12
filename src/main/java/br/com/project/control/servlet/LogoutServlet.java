package br.com.project.control.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{

    public LogoutServlet() {
        super();
    }

    private static final long serialVersionUID=1L;
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = request.getContextPath();
            HttpSession session = request.getSession();
            session.invalidate();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
}