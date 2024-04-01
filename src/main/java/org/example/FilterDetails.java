package org.example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/addUsersList","/editUsersList"})
public class FilterDetails implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String city=request.getParameter("city");
        String role=request.getParameter("role");
        if(id != null && !id.isEmpty()&&name != null && !name.isEmpty()&& city!=null && !city.isEmpty() && role!=null && !role.isEmpty()){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            if(Objects.equals(request.getServletPath(), "/addUsersList")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("addUsers.jsp");
                request.setAttribute("errorInsertDetails", "The fields should not be empty");
                requestDispatcher.forward(servletRequest, servletResponse);
            }

            if(Objects.equals(request.getServletPath(), "/editUsersList")){
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("editUser.jsp");
                request.setAttribute("errorEditDetails", "The fields should not be empty");
                requestDispatcher.forward(servletRequest,servletResponse);
            }

        }
    }
}
