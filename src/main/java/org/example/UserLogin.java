package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/userLoginClass",loadOnStartup = 1)
public class UserLogin extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(UserRegister.class);
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userName=req.getParameter("userName");
        String userPassword=req.getParameter("userPassword");
//        System.out.println(userName);
//        System.out.println(userPassword);


        try {
            if(DAO.authenticateUserLogin(userName,userPassword)){
                logger.info("User Logged In");
                //System.out.println(userName);
                HttpSession session=req.getSession();
                session.setAttribute("uname",userName);
                session.setAttribute("pass",userPassword);
                res.sendRedirect("/userList");
            }else{
                res.sendRedirect("userLogin.jsp");
            }
        } catch (SQLException e) {
            res.sendRedirect("error.jsp");
        }


    }

    public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        super.service(req,res);
       // res.getWriter().println("service method");
    }
}
