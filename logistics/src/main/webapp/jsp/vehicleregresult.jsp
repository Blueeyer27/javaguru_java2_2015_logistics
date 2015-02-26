<%@ page import="lv.javaguru.java2.domain.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17.02.2015
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Vehicle registration confirmation</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<h1><div align="center">Registration successful</div></h1>
<div align="center">
  <table align="center" border="1" width="450">
    <tr>
      <td width="200"><b>Vehicle Name</b></td>
      <td width="200"><b>Vehicle Type</b></td>
      <td width="200"><b>Capacity</b></td>
      <td width="200"><b>Status</b></td>
      </tr>

<%
  Vehicle vehicle = (Vehicle)request.getAttribute("model");
%>
    <td width="200"><%=vehicle.getName()%></td>
    <td width="200"><%=vehicle.getType()%></td>
    <td width="200"><%=vehicle.getCapacity()%></td>
    <td width="200"><%=vehicle.getStatus()%></td>
    <br>

</body>
</html>
