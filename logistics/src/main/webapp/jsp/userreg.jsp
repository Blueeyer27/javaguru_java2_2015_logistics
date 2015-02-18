<%@ page import="lv.javaguru.java2.domain.Company" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.database.jdbc.CompanyDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Add new user</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<div align="center">
    <form method="post" action="userRegResult">
        Login:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="login" /><br/>
        <br/>
        Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="password" /><br/>
        <br/>
        Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="firstname" /><br/>
        <br/>
        Surname:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="lastname" /><br/>
        <br/>
        Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="email" /><br/>
        <br/>
        Phone:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="phone" /><br/>
        <br/>
        Company:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <select name="companyid">
        <%
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        List<Company> companyes = companyDAO.getAll();
        if (companyes.size()>0)
            for (Company company : companyes) {
        %>
                <option value=<%=company.getCompanyId()%>> id=<%=company.getCompanyId()%> <%=company.getName()%>
        <%  }
            else {%>
                <option value="1">! id=1 NoCompany found
        <%}%>
        </select><br/>
        <br/>
        <input type="submit" value="register" />
    </form>
</div>
</body>
</html>

