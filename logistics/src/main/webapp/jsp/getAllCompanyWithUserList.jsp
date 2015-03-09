
<%@ page import="java.util.List" %>

<%@ page import="lv.javaguru.java2.domain.Company" %>
<%@ page import="lv.javaguru.java2.domain.User" %>

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
  <link rel="stylesheet" type="text/css" href="../style.css">
  <title>Vehicle list</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<div align="center">
  <table align="center" border="1" width="450">
    <tr>
    <td width="200"><b>Company Name</b></td>
    </tr>

<%
/*
            List<Company> companyes = (List<Company>)request.getAttribute("model");
            for (Company company : companyes) {
            List<User> userList = company.getUserList();
            for (User user : userList) {
*/
            List<Company> companyes = (List<Company>)request.getAttribute("model");
            for (int i = 0; i < companyes.size(); i ++) {
                Company company = companyes.get(i);

            List<User> userList = company.getUserList();
            for (User user : userList) {
                System.out.println("  " + user.getLogin());



%>
      <tr>
        <td width="200"><%=company.getName()%> AND <%=user.getLogin()%></td>
      </tr>

<%

}
}%>


</body>
</html>
