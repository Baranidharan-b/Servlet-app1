package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DAO {
   private static final Connection connection;
   private static final String AUTHENTICATE_LOGIN="select * from userlogintable where username=? and userpassword=?;";

   private static final String REGISTER_NEW_USER="insert into userlogintable (username,userpassword) values(?,?);";

   private static final String GET_USER_DETAILS="select * from userlisttable order by id asc;";

   private static final String EDIT_USER="update userlisttable set name= ? , city = ? ,role = ? where id = ? ;";
   private static final String ADD_USER="insert into userlisttable (id,name,city,role) values(?,?,?,?);";
   private static final String DELETE_USER="delete from userlisttable where id=?;";

    static {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userinfo", "postgres", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean authenticateUserLogin(String userName,String userPass) throws SQLException {


            PreparedStatement preparedStatement=connection.prepareStatement(AUTHENTICATE_LOGIN);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,userPass);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }



    }

    public static void registerNewUser(String userName,String userPassword) throws SQLException {


        PreparedStatement preparedStatement=connection.prepareStatement(REGISTER_NEW_USER);
        preparedStatement.setString(1,userName);
        preparedStatement.setString(2,userPassword);
        preparedStatement.executeUpdate();


    }

    public static ResultSet getUserDetails() throws SQLException {

        PreparedStatement preparedStatement=connection.prepareStatement(GET_USER_DETAILS,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=preparedStatement.executeQuery();
//        while(rs.next()){
//            System.out.println(rs.getInt("id"));
//            System.out.println(rs.getString("name"));
//            System.out.println(rs.getString("city"));
//            System.out.println(rs.getString("role"));
//        }
        return rs;
    }

    public static void editUserList(User usrObj) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement(EDIT_USER);
        preparedStatement.setString(1,usrObj.getName());
        preparedStatement.setString(2, usrObj.getCity());
        preparedStatement.setString(3, usrObj.getRole());
        preparedStatement.setInt(4,usrObj.getId());
        preparedStatement.executeUpdate();
    }

    public static void addUserToList(User usrObj) throws SQLException {
        PreparedStatement preparedStatement= connection.prepareStatement(ADD_USER);
        preparedStatement.setInt(1,usrObj.getId());
        preparedStatement.setString(2,usrObj.getName());
        preparedStatement.setString(3,usrObj.getCity());
        preparedStatement.setString(4,usrObj.getRole());
        preparedStatement.executeUpdate();
    }

    public static void deleteUserFromList(int id) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement(DELETE_USER);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }


    public static List<User> getUserDetailList() throws SQLException {

        List<User> listOfUsers=new ArrayList<>();

        PreparedStatement preparedStatement=connection.prepareStatement("select * from userlisttable order by id asc;",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=preparedStatement.executeQuery();
        while(rs.next()){

            User usr=new User(rs.getInt("id"),rs.getString("name"),rs.getString("city"),rs.getString("role"));
            listOfUsers.add(usr);
        }


        return listOfUsers;

    }
}
