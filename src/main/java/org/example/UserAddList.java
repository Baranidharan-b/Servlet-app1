package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addUsersList")
public class UserAddList extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(UserAddList.class);
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        String city= req.getParameter("city");
        String role=req.getParameter("role");
        User usrObj=new User(id,name,city,role);

        try {

            DAO.addUserToList(usrObj);
            logger.info("User Added");
            res.sendRedirect("/userList");
        } catch (SQLException e) {
            if(e.getMessage().contains("duplicate key value violates unique constraint \"userlisttable_pkey\"")){
                req.setAttribute("errorMessage","ID is already present");
                RequestDispatcher requestDispatcher= req.getRequestDispatcher("addUsers.jsp");
                requestDispatcher.forward(req,res);
                System.out.println("Git");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
