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
    <title></title>
</head>
<body>
<h1>ETO USPEH!!!</h1>

<%
  Vehicle vehicle = (Vehicle)request.getAttribute("model");
%>
<td>Vehicle name: <%=vehicle.getName()%></td>
<br>
i tak daleje....

</body>
</html>
