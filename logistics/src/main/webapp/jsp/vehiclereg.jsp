<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.02.2015
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Registration successful</title>
</head>
<body>
<div align = "center">
<h1>Vehicle created</h1>
<p>
  <h1>DATA:</h1>

    <%
  String name = (String)request.getAttribute("name");
  String type = (String)request.getAttribute("type");
  String plateNum = (String)request.getAttribute("platenumber");
  String trailerNum = (String)request.getAttribute("trailernumber");
  String capacity = (String)request.getAttribute("capacity");

  out.print("<h1>Name: " + name + "<br/>");
  out.print("Type: " + type + "<br/>");
  out.print("Plate number: " + plateNum + "<br/>");
  out.print("Trailer number: " + trailerNum + "<br/>");
  out.print("Capacity: " + capacity + "<br/></h1>");
  %>
</div>


</body>
</html>
