package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/editUsersList")
public class UserEditList extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(UserEditList.class);
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        int id= Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        String city=req.getParameter("city");
        String role=req.getParameter("role");
        User usrObj=new User(id,name,city,role);


        try {

            DAO.editUserList(usrObj);
            logger.info("user edited");
            res.sendRedirect("/userList");

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }
}
