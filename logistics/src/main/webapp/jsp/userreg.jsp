<%--
 * Created by andre on 10.02.2015.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>user registration</title>
</head>
<body>
<h1>user created succesfully!</h1>
<p>
<h1>DATA:</h1>

<%
    String login = (String)request.getAttribute("login");
    String password = (String)request.getAttribute("password");
    String firstname = (String)request.getAttribute("firstname");
    String lastname = (String)request.getAttribute("lastname");
    String email = (String)request.getAttribute("email");
    String  phone = (String)request.getAttribute("phone");

    out.print("<h1>Login: " + login + "<br/>");
    out.print("Password: " + password + "<br/>");
    out.print("Name: " + firstname + "<br/>");
    out.print("Surname:: " + lastname + "<br/>");
    out.print("Email: " + email + "<br/>");
    out.print("Phone: " + phone + "<br/></h1>");
%>


</body>
</html>
