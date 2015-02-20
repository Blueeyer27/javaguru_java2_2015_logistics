<%@ page import="lv.javaguru.java2.servlet.mvc.MVCModel" %>
<%@ page import="lv.javaguru.java2.domain.Cargo" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>New CARGO created!</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<div align="center">
    <table align="center" border="3" width="700">
        <tr>
            <td width="600"><b>New cargo created!</b></td>
        <tr>
        </tr>
    </table>

    <BR>
    <BR>


    <table align="center" border="1" width="700">
        <tr>
            <td width="200"><b>Cargo data:</b></td>
        <tr>
        <tr>
            <td width="200"><b>Type</b></td>
            <td width="200"><b>Weight</b></td>
            <td width="200"><b>Load address</b></td>
            <td width="200"><b>Unload address</b></td>
            <td width="200"><b>Load date</b></td>
            <td width="200"><b>Unload date</b></td>
            <td width="200"><b>User ID</b></td>
        </tr>
        <%
            Cargo cargo = (Cargo)request.getAttribute("model");
        %>
        <tr>
            <td width="200"><%=cargo.getVehicleType()%></td>
            <td width="200"><%=cargo.getWeight()%></td>
            <td width="200"><%=cargo.getLoadAddress()%></td>
            <td width="200"><%=cargo.getUnloadAddress()%></td>
            <td width="200"><%=cargo.getLoadDate()%></td>
            <td width="200"><%=cargo.getUnloadDate()%></td>
            <td width="200"><%=cargo.getUserId()%></td>
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