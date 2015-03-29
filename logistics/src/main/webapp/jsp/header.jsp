<%@ page import="lv.javaguru.java2.domain.User" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Header</title>
</head>
<script language="JavaScript">
    function formop()
    {
        form = window.open("resetPassword", "name", "tollbar=no, menubar=no, fullscreen=no, scrollbars=yes, top=120, left=150, width=330, height=300");
    }
</script>
<body>
<%  User user = (User)session.getAttribute("user");
    if (user != null) {%>
        <div align="right"><U><b>Hello, <%=user.getFirstName()%> <%=user.getLastName()%>!</b></U></div>
        <div align="right"><a href="jsp/logout.jsp"><U>Exit</U></a></div>
        <div align="left"><a href="javascript:formop();" target="" style="text-decoration:none"><U>Reset password</U></a></div>
        <%--<div align="left"><a href="resetPassword" target="_blank" style="text-decoration:none"><U>Reset password</U></a></div>--%>

        <%if (user.getUserCompanyType().equals("admin")) { //hardcode%>
            <div align="center"><img src="images/adminlogo.jpg"/></div><hr />
            <div align="right"><a href="../java2/admin">Back to admin home</a></div><hr />
        <%} else{%>
            <div align="center"><img src="images/indexlogo.jpg"/></div><hr />
            <div align="right"><a href="../java2">Back to home</a></div><hr />
        <%}%>
    <%} else{%>
        <div align="center"><img src="images/indexlogo.jpg"/></div><hr />
        <div align="right"><a href="../java2">Back to home</a></div><hr />
    <%}%>
</body>
</html>

