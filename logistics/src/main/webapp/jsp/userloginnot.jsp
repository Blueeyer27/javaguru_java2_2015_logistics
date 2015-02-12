<%--
 * Created by andre on 10.02.2015.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>:( user login check</title>
</head>
<body>
<h1> :( </h1>
<h1>NO! user NOT EXIST or incorrect password! </h1>
<p>
<h1>DATA:</h1>

<%
    String login = (String)request.getAttribute("login");
    String password = (String)request.getAttribute("password");

    out.print("<h1>Username: " + login + "<br/></h1>");
%>

</body>
</html>
