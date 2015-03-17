<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.Value" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>New Vehicle registration</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
    <%
    Map<String, Object> modelHashMap = (Map<String, Object>) request.getAttribute("model");
    List<User> userList = (ArrayList<User>) modelHashMap.get("users");
    List<Value> vehicleTypes = (ArrayList<Value>) modelHashMap.get("vehicleTypes");
    %>
<div align="center">
    <form method="post" action="vehicleRegResult">
        Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" /><br/>
        <br/>
        Type:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <select name="type">
            <%for (Value value : vehicleTypes) {%>
                <option value=<%=value.getValue()%>>&nbsp;<%=value.getValue()%>
            <%}%>
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
