<%@ page import="lv.javaguru.java2.domain.Company" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Add new user wizard</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="../java2">Back to home</a></div>
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
        Company:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <BR><BR>
        <select name="companyid">
            <%
            List<Company> companies = (List<Company>)request.getAttribute("model");
            if (companies.size()>0)
                for (Company company : companies) {
                %>
                    <option value=<%=company.getCompanyId()%>> id=<%=company.getCompanyId()%> <%=company.getName()%> type=<%=company.getType()%>
                <%  }
            else {%>
                <option value="1">! id=1 NoCompany found

                <script language="Javascript">
                    <!--
                    alert ("Sorry, NO company found in DB! :( You can use default id=1 instead")
                    //-->
                </script>
            <%}%>
        </select><br/>
        <br/>
        <input type="submit" value="register" />
    </form>
</div>
</body>
</html>

