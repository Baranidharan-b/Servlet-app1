package org.example;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/userRegisterClass")
public class UserRegister extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(UserRegister.class);
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userName=req.getParameter("userName");
        String userPassword=req.getParameter("userPassword");



        try {
            if(userName != null && !userName.isEmpty()&& userPassword != null && !userPassword.isEmpty()) {
                DAO.registerNewUser(userName, userPassword);
                logger.info("User Registered");
                HttpSession session=req.getSession();
                session.setAttribute("unmae",userName);
                session.setAttribute("pass",userPassword);
                res.sendRedirect("/userList");
            }else{

                res.sendRedirect("registered.jsp");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
