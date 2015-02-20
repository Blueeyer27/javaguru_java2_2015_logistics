<%@ page import="lv.javaguru.java2.domain.Company" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.database.jdbc.CompanyDAOImpl" %>
<%@ page import="lv.javaguru.java2.database.jdbc.UserDAOImpl" %>
<%@ page import="lv.javaguru.java2.domain.User" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Add new user wizard</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />




<div align="center">
    <form method="post" action="cargoRegResult">
        Vehicle type:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <select name="type">
            <option value="platform" selected>&nbsp;platform
            <option value="refrigerator" >&nbsp;refrigerator
            <option value="tilt" >&nbsp;tilt
        </select><br/>
        <br/>
        Weight:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="weight" /><br/>
        <br/>
        Load address:&nbsp;&nbsp;&nbsp;<input type="text" name="loadaddress" /><br/>
        <br/>
        Unload address:<input type="text" name="unloadaddress" /><br/>
        <br/>
        Load date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="date" name="loaddate" /><br/>
        <br/>
        Unload date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="date" name="unloaddate" /><br/>
        <br/>
        User:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <select name="userid">
            <%
                UserDAOImpl userDAO = new UserDAOImpl();
                List<User> users = userDAO.getAll();
                if (users.size()>0)
                    for (User user : users) {
            %>
            <option value=<%=user.getUserId()%>> id=<%=user.getUserId()%> <%=user.getFirstName()%> <%=user.getLastName()%> (<%=user.getLogin()%>)
                    <%  }
            else {%>
            <option value="1">! id=1 NoUser found
                    <%}%>
        </select><br/>
        <br/>
        <input type="submit" value="register" />
    </form>
</div>
</body>
</html>

