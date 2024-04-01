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
            form {
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                padding: 20px;
                width: 300px;
                margin: 50px auto;
            }
            input[type="number"],
            input[type="text"],
            input[type="submit"] {
                display: block;
                width: 100%;
                margin-bottom: 10px;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
                box-sizing: border-box;
            }
            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
            .errorMessage {
                color: red;
                font-weight: bold;
                text-align: center; /* Center-align the error message */
                margin-top: 20px; /* Add some space above the error message */
                font-size: 18px; /* Increase font size */
                text-transform: uppercase; /* Convert text to uppercase */
                padding: 10px; /* Add padding around the error message */
                background-color: #ffe6e6; /* Light red background color */
                border: 2px solid #FCCLA; /* Red border */
                border-radius: 5px; /* Rounded corners */
            }

    </style>
</head>
<body>
<%
if(request.getAttribute("errorEditDetails")!=null){%>
<p class="errorMessage"><%=request.getAttribute("errorEditDetails")%></p>
<%
}
%>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
if(session.getAttribute("uname")==null){
    response.sendRedirect("userLogin.jsp");
}
%>
<%String id=request.getParameter("id");%>


<form action="/editUsersList" method="POST">
    <input type="hidden" name="id" value="<%=id%>"><br>
    Name :<input type="text" name="name"><br>
    City :<input type="text" name="city"><br>
    Role :<input type="text" name="role"><br>
    <input type="submit" value="Change">
</form>
</body>
</html>
