<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>New cargo registration</title>
</head>
<jsp:include page="header.jsp" />
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

