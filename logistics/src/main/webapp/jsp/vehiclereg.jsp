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
    <title>Vehicle registration</title>
</head>
<body>
<div align="center">
  <form method="post" action="../vehicleregresult">
    Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" /><br/>
    <br/>
    Type:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <select name="type">
      <option value="platform" selected>&nbsp;platform
      <option value="refrigerator" >&nbsp;refrigerator
      <option value="tilt" >&nbsp;tilt
    </select><br/>
    <br/>
    Plate number:&nbsp;&nbsp;&nbsp;<input type="text" name="platenumber" /><br/>
    <br/>
    Trailer number:<input type="text" name="trailernumber" /><br/>
    <br/>
    Capacity:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="capacity" /><br/>
    <br/>
    <input type="submit" value="register" />
  </form>
</div>

</body>
</html>
