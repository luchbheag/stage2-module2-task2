package com.example.filter;

import com.example.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/users/*"},
initParams = {
        @WebInitParam(name = "login", value="user;admin")
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String login = request.getParameter("login");
        if (Users.getInstance().getUsers().contains(login)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/login.jsp");
        }
    }
}