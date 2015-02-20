<%@ page import="lv.javaguru.java2.domain.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 19.02.2015
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>nothing special</h1>
<%
  List<Vehicle> vehicleList = (ArrayList<Vehicle>)request.getAttribute("model");
  Vehicle vehicle=null;
  for(int i=0;i<vehicleList.size();i++){
    vehicle = vehicleList.get(i);
    out.print("<h1>" + "vehicle name: " +vehicle.getName() + "</h1>");
     }

%>



</body>
</html>
