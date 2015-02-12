<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Cargo registration successful</title>
</head>
<body>
<div align = "center">
<h1>New cargo created!</h1>
<p>
    <h1>DATA:</h1>


    <%
    String type = (String)request.getAttribute("type");
    String weight = (String)request.getAttribute("weight");
    String loadaddress = (String)request.getAttribute("loadaddress");
    String unloadaddress = (String)request.getAttribute("unloadaddress");
    String loaddate = (String)request.getAttribute("loaddate");
    String unloaddate = (String)request.getAttribute("unloaddate");

    out.print("<h2>Vehicle type: " + type + "<br/></h2>");
    out.print("<h2>Weight: " + weight + "<br/></h2>");
    out.print("<h2>Load address: " + loadaddress + "<br/></h2>");
    out.print("<h2>Unload address: " + unloadaddress + "<br/></h2>");
    out.print("<h2>Load date: " + loaddate + "<br/></h2>");
    out.print("<h2>Unload date: " + unloaddate + "<br/></h2>");
  %>

</div>


</body>
</html>
