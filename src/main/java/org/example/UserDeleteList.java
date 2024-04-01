package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteUser")
public class UserDeleteList extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(UserDeleteList.class);
    public void doGet(HttpServletRequest req, HttpServletResponse res){
            int id= Integer.parseInt(req.getParameter("id"));
        try {

            DAO.deleteUserFromList(id);
            logger.info("User Deleted");
            res.sendRedirect("/userList");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
