package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userLogoutClass")
public class UserLogout extends HttpServlet {
    Logger logger= LoggerFactory.getLogger(UserLogout.class);
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session=req.getSession();
        session.removeAttribute("uname");
        session.removeAttribute("pass");
        session.invalidate();
        logger.info("User Logged Out");
        res.sendRedirect("userLogin.jsp");
    }
}
