package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/userList")
public class UserList extends HttpServlet {
    Logger logger= LoggerFactory.getLogger(UserList.class);
   public void doGet(HttpServletRequest req, HttpServletResponse res){
       try {
//           System.out.println(req+" "+res);
          List<User> userDetails= DAO.getUserDetailList();
           req.setAttribute("userDetails",userDetails);
           RequestDispatcher rqdis=req.getRequestDispatcher("userDisplay.jsp");
           logger.info("User Displayed");
           rqdis.forward(req,res);
           //System.out.println("debug");
//           res.sendRedirect("userDisplay.jsp");
       } catch (SQLException | IOException | ServletException e) {
           throw new RuntimeException(e);
       }

   }
}
