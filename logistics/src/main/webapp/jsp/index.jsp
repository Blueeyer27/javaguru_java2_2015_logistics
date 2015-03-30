<%@ page import="lv.javaguru.java2.domain.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>-= CARGO SYSTEM=-</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<div align="right" style="background-image:url(.jpg);border: 3px dotted grey;position:absolute;top:110;left:100;height:120;width:140;">
    <% User user = (User)session.getAttribute("user"); %>
    <table align="center" border="0" width="260">
        <tr align="center">
            <%if (user == null) {%>
                <tr align="center">
        <td><form method="get" action="jsp/login.jsp"><button type="submit">Login</button></form></td>
        <%--<td><form method="get" action="admin"><button type="submit">Admin login</button></form></td>--%>
                </tr>
            <%} else {%>
                <tr align="center">
                    <td colspan="3"><b>You are logged in as <%=user.getFirstName()%> <%=user.getLastName()%></b></td>
                </tr>
                <tr align="center">
                    <td><form method="get" action="userLogin"><button type="submit">My Profile </button></form></td>
                    <td><form method="get" action="jsp/logout.jsp"><button type="submit">Exit</button></form></td>
                    <td><form method="get" target="_blank" action="userReg"><button type="submit">Register new</button></form></td>
                </tr>
            <%}%>
    </table>
</div>
<BR><BR><BR><BR>
<BR><BR><BR><BR>
<%--<hr />--%>
<div align="center"><img src="images/gold.globe.spin.gif"/></div>
<div align="center">...connecting people and business....</div>
</body>
</html>