<%--
 * Created by andre on 10.02.2015.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>:) user login check</title>
</head>
<body>
<h1> :) </h1>
<h1>Welcome!</h1>
<p>
<h2>Hello, <%out.print(request.getAttribute("firstName"));%>, <%out.print(request.getAttribute("lastName"));%>,</h2>
<h2>Your login in system is "<%out.print(request.getAttribute("login"));%>"</h2>
<h2>Your e-mail: <%out.print(request.getAttribute("eMail"));%>"</h2>
<h2>You work in company with id = : <%out.print(request.getAttribute("company"));%>"</h2>

</body>
</html>
