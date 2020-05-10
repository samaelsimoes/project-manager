package br.com.project.control.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.project.control.entity.pojo.User;
import br.com.project.control.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private static final long serialVersionUID=1L;
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getContextPath();

        try {
            if (!request.equals("")) {
                List<String> credUser = new ArrayList<String>();

                credUser.add( request.getParameter("login") );
                credUser.add( request.getParameter("password") );

                User user = new LoginService(credUser).validateUser();

                Map<String, String> msg = new HashMap<String, String>();
                HttpSession sectionUser = request.getSession();

                if (user != null) {

                    sectionUser.setAttribute("sectionuser", user);//setAtribute I'm putting my UserPojo user object in session
                    Cookie cookiettipo = new Cookie("type", String.valueOf(user.getType()));
                    Cookie cookieid = new Cookie("id", String.valueOf(user.getId()));
                    cookiettipo.setMaxAge(3600);
                    cookieid.setMaxAge(3600);
                    response.addCookie(cookiettipo);
                    response.addCookie(cookieid);

                    msg.put("msg", " Login realizado com sucesso ! ");
                    msg.put("acesso", url+ "/project/private/paginaInicial.html");
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {

                    sectionUser.invalidate();// invalid session
                    msg.put("msg", "Login invalido");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    //Status code (403) indicating the server understood the request but refused to fulfill it.
                }

                sectionUser.setMaxInactiveInterval(600);
                String json = new ObjectMapper().writeValueAsString(msg);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        }catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}