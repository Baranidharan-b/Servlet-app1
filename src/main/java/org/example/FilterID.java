package org.example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns ={"/addUsersList"})
public class FilterID implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        if(Integer.parseInt(req.getParameter("id"))<1){
            //servletResponse.getWriter().println("ID should not be less than 1");
           RequestDispatcher requestDispatcher=req.getRequestDispatcher("/addUsers.jsp");
           req.setAttribute("negative_value","true");
           requestDispatcher.forward(servletRequest,servletResponse);

        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
