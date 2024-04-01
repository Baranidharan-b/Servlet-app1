<%@ page language="java" import="java.util.List,org.example.User" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Display</title>
    <style>
        body {
                   font-family: Arial, sans-serif;
                   background-color: #f4f4f4;
                   margin: 0;
                   padding: 0;
                   padding-top: 20px; /* Add padding to the top */
                   text-align: center; /* Center-align content */
               }
               .errorMessage {
                   color: red;
                   font-weight: bold;
               }
               a {
                   display: inline-block;
                   margin-bottom: 10px; /* Add space between "Add Users" link and table */
                   padding: 10px 20px;
                   background-color: #4CAF50;
                   color: #fff;
                   text-decoration: none;
                   border-radius: 5px;
               }
               a:hover {
                   background-color: #45a049;
               }
               table {
                   width: 80%;
                   margin: 0 auto; /* Center the table */
                   border-collapse: collapse;
               }
               th, td {
                   padding: 8px;
                   border-bottom: 1px solid #ddd;
               }
               th {
                   background-color: #4CAF50;
                   color: white;
               }
               tr:nth-child(even) {
                   background-color: #f2f2f2;
               }
    </style>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if(session.getAttribute("uname")==null){
        response.sendRedirect("userLogin.jsp");
    }
%>
<a href="addUsers.jsp">Add Users</a>
<% if(request.getAttribute("errorMessage") != null) { %>
    <p class="errorMessage"><%= request.getAttribute("errorMessage") %></p>
<% } %>

<table>
<tr>
<th>ID</th>
<th>Name</th>
<th>City</th>
<th>Role</th>
<th>Edit Users</th>
<th>Delete Users</th>
</tr>
<%
List<User> usrList = (List<User>) request.getAttribute("userDetails");
int pageSize = 5; // Number of records to display per page
int totalRecords = usrList.size(); // Total number of records
int totalPages = (int) Math.ceil((double) totalRecords / pageSize); // Calculate total pages

// Get the requested page number (if specified)
int currentPage = 1; // Default to the first page
String pageParam = request.getParameter("page");
if (pageParam != null) {
    currentPage = Integer.parseInt(pageParam);
    if (currentPage < 1) {
        currentPage = 1;
    } else if (currentPage > totalPages) {
        currentPage = totalPages;
    }
}

// Calculate the starting and ending indexes of records for the current page
int startIndex = (currentPage - 1) * pageSize;
int endIndex = Math.min(startIndex + pageSize, totalRecords);

// Iterate through records for the current page
for(int i = startIndex; i < endIndex; i++) {
    User usr = usrList.get(i);
%>
 <tr>
 <td><%= usr.getId() %></td>
 <td><%= usr.getName() %></td>
 <td><%= usr.getCity() %></td>
 <td><%= usr.getRole() %></td>
 <td><a href="editUser.jsp?id=<%= usr.getId() %>">Edit</td>
 <td><a href="/deleteUser?id=<%= usr.getId() %>">Delete</a></td>
 </tr>
<%
} // End of for loop
%>
</table>
<div>
<%
// Display pagination controls
if (totalPages > 1) {
%>
Page <%= currentPage %> of <%= totalPages %>
<%
    // Previous page link
    if (currentPage > 1) {
%>
    <a href="?page=<%= currentPage - 1 %>">Previous</a>
<%
    }
    // Next page link
    if (currentPage < totalPages) {
%>
    <a href="?page=<%= currentPage + 1 %>">Next</a>
<%
    }
}
%>
</div>
<a href="/userLogoutClass">Logout</a>
</body>
</html>
