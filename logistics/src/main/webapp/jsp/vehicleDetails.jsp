<%@ page import="lv.javaguru.java2.domain.Vehicle" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 20.02.2015
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../style.css">
    <title></title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr/>
<div align="right"><a href="index.html">BACK</a></div>
<hr/>
<div align="center"><h1>Vehicle additional information</h1></div>

<% Vehicle vehicle = (Vehicle)request.getAttribute("model");

%>
<form method="post" action="sendRequestVehicle">
  <div align="center">Vehicle Name: <%=vehicle.getName()%></div>
  <br/>
  <div align="center">Vehicle Type: <%=vehicle.getType()%></div>
  <br/>
  <div align="center">Vehicle Plate Number: <%=vehicle.getplateNumber()%></div>
  <br/>
  <div align="center">Vehicle Trailer Number: <%=vehicle.gettrailerNumber()%></div>
  <br/>
  <div align="center">Vehicle Capacity: <%=vehicle.getCapacity()%></div>
  <br/>
  <div align="center">Vehicle Status: <%=vehicle.getStatus()%></div>
  <br/>
  <div align="center">Vehicle destination, vehicle owner, i bla bla bla.. </div>
  <div align="center">
  <input type="hidden" name="id" value="<%=vehicle.getVehicleId()%>">
  <input type="submit" name="action" value="Send Request">
  </div>
  </form>


</body>
</html>
