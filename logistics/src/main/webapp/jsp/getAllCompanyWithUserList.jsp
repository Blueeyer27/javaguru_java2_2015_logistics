<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.Company" %>
<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page import="lv.javaguru.java2.database.jdbc.CompanyDAOImpl" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="org.springframework.beans.factory.annotation.Qualifier" %>
<%@ page import="lv.javaguru.java2.database.CompanyDAO" %>


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
//            Company company = (Company)request.getAttribute("model");
            List<Company> companyList = (List<Company>)request.getAttribute("model");
            for (Company company : companyList) {
%>
      <tr>
        <td width="200"><%=company.getName()%></td>
      </tr>
<%
            List<User> userList = company.getUserList();
            for (User user : userList) {
                System.out.println("  " + user.getLogin());
%>
      <tr>
        <td width="200"> Users = <%=user.getLogin()%></td>
      </tr>

<%
            }

            }
%>

</body>
</html>
