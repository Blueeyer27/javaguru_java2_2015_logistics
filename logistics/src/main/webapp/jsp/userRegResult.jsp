<%@ page import="lv.javaguru.java2.servlet.mvc.MVCModel" %>
<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>New User created!</title>
</head>
<body>

<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<div align="center">
    <table align="center" border="3" width="700">
        <tr>
            <td width="600"><b>New user created!</b></td>
        <tr>
        </tr>
    </table>

    <BR>
    <BR>

    <table align="center" border="1" width="700">
        <tr>
            <td width="200"><b>User data:</b></td>
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
        <%
            User user = (User)request.getAttribute("model");
        %>
        <tr>
            <td width="200"><%=user.getLogin()%></td>
            <td width="200"><%=user.getPassword()%></td>
            <td width="200"><%=user.getFirstName()%></td>
            <td width="200"><%=user.getLastName()%></td>
            <td width="200"><%=user.getEMail()%></td>
            <td width="200"><%=user.getPhoneNumber()%></td>
            <td width="200"><%=user.getCompany().getCompanyId()%></td>
        </tr>
    </table>
</div>
</body>
</html>


<html>
<script language="Javascript">
    <!--
    alert ("New User created! Urra,  tovarishi-comrads, URRA! :)")
    //-->
</script>
</body>
</html>