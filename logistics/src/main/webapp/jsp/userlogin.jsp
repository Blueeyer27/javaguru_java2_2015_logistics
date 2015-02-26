<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>:) Login successfull!</title>
</head>
<body>
<% User user = (User)session.getAttribute("user");%>
<div align="right"><b>Hello, <%=user.getLogin()%>
    <%=user.getFirstName()%>
    (<%=user.getLastName()%>)!
    <br>[Type: <%=session.getAttribute("userType")%>]</b></div>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<div align="center">
    <table align="center" border="1" width="700">
        <tr>
            <td width="400"><b>Hi, :) <%=user.getFirstName() + " " + user.getLastName()%> </b></td>
        <tr>
        <tr>
            <td width="400"><b>USER-DATA:</b></td>
        <tr>
        <tr>
            <td width="200"><b>Login</b></td>
            <td width="200"><b>Password</b></td>
            <td width="200"><b>Firstname</b></td>
            <td width="200"><b>Lastname</b></td>
            <td width="200"><b>Email</b></td>
            <td width="200"><b>Phone</b></td>
            <td width="200"><b>Company ID</b></td>
        </tr>
        <tr>
            <td width="200"><%=user.getLogin()%></td>
            <td width="200"><%=user.getPassword()%></td>
            <td width="200"><%=user.getFirstName()%></td>
            <td width="200"><%=user.getLastName()%></td>
            <td width="200"><%=user.getEMail()%></td>
            <td width="200"><%=user.getPhoneNumber()%></td>
            <td width="200"><%=user.getCompanyId()%></td>
        </tr>
    </table>
</div>
</body>
</html>

<html>
<head>
    <title>MVC</title>
</head>
</body>
</html>