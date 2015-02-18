<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>:( Login UN-successfull!</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<div align="center">
    <table align="center" border="1" width="700">

        <tr>
            <td width="400"><b>Hi, :( UN-successfull!</b></td>
        <tr>
        <tr>
            <td width="700"><b>user NOT EXIST or incorrect password!</b></td>

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
            <td width="200"><%=(String)request.getAttribute("model")%></td>
            <td width="200"></td>
            <td width="200"></td>
            <td width="200"></td>
            <td width="200"></td>
            <td width="200"></td>
            <td width="200"></td>
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