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
    <title>New VEHICLE created!</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />

<h1><div align="center">Registration successfull!</div></h1>

<div align="center">
    <table align="center" border="3" width="700">
        <tr>
            <td width="600"><b>New vehicle created!</b></td>
        <tr>
        </tr>
    </table>

  <table align="center" border="1" width="700">
    <tr>
      <td width="200"><b>Vehicle ID</b></td>
      <td width="200"><b>Vehicle Name</b></td>
      <td width="200"><b>Vehicle Type</b></td>
      <td width="200"><b>Plate Nr</b></td>
      <td width="200"><b>Trailer Nr</b></td>
      <td width="200"><b>Capacity</b></td>
      <td width="200"><b>Status</b></td>
      <td width="200"><b>USER ID</b></td>
      <td width="200"><b>COMPANY ID</b></td>
      </tr>

<%
  Vehicle vehicle = (Vehicle)request.getAttribute("model");
%>
    <td width="200"><%=vehicle.getVehicleId()%></td>
    <td width="200"><%=vehicle.getName()%></td>
    <td width="200"><%=vehicle.getType()%></td>
    <td width="200"><%=vehicle.getplateNumber()%></td>
    <td width="200"><%=vehicle.gettrailerNumber()%></td>
    <td width="200"><%=vehicle.getCapacity()%></td>
    <td width="200"><%=vehicle.getStatus()%></td>
    <td width="200"><%=vehicle.getUser().getUserId()%></td>
    <td width="200"><%=vehicle.getUser().getCompany().getCompanyId()%></td>
    <br>

</body>
</html>
<script language="Javascript">
    <!--
    alert ("New VEHICLE record inserted into DB!")
    //-->
</script>
