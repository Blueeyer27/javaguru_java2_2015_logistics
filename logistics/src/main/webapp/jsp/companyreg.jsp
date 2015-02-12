<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Registration successful</title>
</head>
<body>
<div align = "center">
<h1>New company created!</h1>
<p>
    <%
    String name = (String)request.getAttribute("name");
    String regNumber = (String)request.getAttribute("regNumber");
    String regAddress = (String)request.getAttribute("regAddress");
    String actualAddress = (String)request.getAttribute("actualAddress");
    String bank = (String)request.getAttribute("bank");
    String iban = (String)request.getAttribute("iban");
    String country = (String)request.getAttribute("country");
    String type = (String)request.getAttribute("type");

    out.print("<h2>Name: " + name + "<br/></h2>");
    out.print("<h2>Registration number: " + regNumber + "<br/></h2>");
    out.print("<h2>Registration address: " + regAddress + "<br/></h2>");
    out.print("<h2>Actual address: " + actualAddress + "<br/></h2>");
    out.print("<h2>Bank: " + bank + "<br/></h2>");
    out.print("<h2>IBAN: " + iban + "<br/></h2>");
    out.print("<h2>Country: " + country + "<br/></h2>");
    out.print("<h2>Type: " + type + "<br/></h2>");
  %>

</div>


</body>
</html>
