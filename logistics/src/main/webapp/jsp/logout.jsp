<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="refresh" content="3;url=/java2/index.html" />
    <%
    if (((User) session.getAttribute("user")) != null) {
        User user = (User)session.getAttribute("user");
    %>
        <title>logout. Bye, <%=user.getLogin()%>!</title>

    <%}%>
    <link href="styles/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="content_wrapper">
    <div id="content">
        <% session.invalidate(); %>
        <div id="column_w530">
            <div class="header_02">Logout</div>
            <p>You have successfully logged out.<br>
                You will be redirected automatically in 3 seconds...</p>
            <div class="margin_bottom_20"></div>
            <div class="cleaner"></div>
        </div>

        <div class="cleaner"></div>
    </div>
</div>
</body>
</html>