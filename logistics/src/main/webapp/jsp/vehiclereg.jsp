<%@ page import="lv.javaguru.java2.domain.Company" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.database.jdbc.CompanyDAOImpl" %>
<%@ page import="lv.javaguru.java2.database.jdbc.UserDAOImpl" %>
<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>New Vehicle registration</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />




<div align="center">
    <form method="post" action="vehicleRegResult">
        Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" /><br/>
        <br/>
        Type:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <select name="type">
            <option value="platform" selected>&nbsp;platform
            <option value="refrigerator" >&nbsp;refrigerator
            <option value="tilt" >&nbsp;tilt
        </select><br/>
        <br/>
        Plate number:&nbsp;&nbsp;&nbsp;<input type="text" name="platenumber" /><br/>
        <br/>
        Trailer number:<input type="text" name="trailernumber" /><br/>
        <br/>
        Capacity:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="capacity" /><br/>
        <br/>
        User:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <select name="userid">
            <%

                List<User> userList = (ArrayList<User>)request.getAttribute("model");

                if (userList.size()>0)
                    for (User user : userList) {
            %>
            <option value=<%=user.getUserId()%>> id=<%=user.getUserId()%> <%=user.getFirstName()%> <%=user.getLastName()%> (<%=user.getLogin()%>)
                    <%  }
            else {%>
            <option value="1">! id=1 NoUser found

                <script language="Javascript">
                    <!--
                    alert ("Sorry, NO user found in DB! :( You can use default id=1 instead")
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
