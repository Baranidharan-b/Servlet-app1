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
if(request.getAttribute("errorInsertDetails")!=null){%>
<p class="errorMessage"><%=request.getAttribute("errorInsertDetails")%></p>
<%
}
%>
<%

if(request.getAttribute("negative_value")!=null){%>
<p class="errorMessage"><%="Negative values are not allowed"%></p>
<%
}
%>
<%
if(request.getAttribute("errorMessage")!=null){%>
<p class="errorMessage"><%=request.getAttribute("errorMessage")%></p>
<%
//request.removeAttribute("errorMessage");
}%>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
  if(session.getAttribute("uname")==null){
    response.sendRedirect("userLogin.jsp");
  }
%>

<form action="/addUsersList" method="POST">
    <label for="id">ID:</label>
    <input type="number" id="id" name="id">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name">
    <label for="city">City:</label>
    <input type="text" id="city" name="city">
    <label for="role">Role:</label>
    <input type="text" id="role" name="role">
    <input type="submit" value="Add">
</form>



</body>
</html>