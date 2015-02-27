<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>:) Login successfull!</title>
</head>
<body>
<% User user = (User)session.getAttribute("user");%>
<div align="right"><U><b>Hello, <%=user.getLogin()%>
    <%=user.getFirstName()%>
    (<%=user.getLastName()%>)!
    -----[Type: <%=session.getAttribute("userType")%>]</b></U></div>
<div align="right"><a href="logout.jsp"><U>Exit</U></a></div>
<div align="center"><img src="../images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="../index.html">BACK</a></div>
<hr />
<div align="center">
    <table align="center" border="1" width="700">
        <tr>
            <td width="400"><b>Hi, :) <%=user.getFirstName() + " " + user.getLastName()%> </b></td>
        <tr>
        <tr>
            <td width="400"><b>USER-DATA:</b></td>
        <tr>
        <tr>
            <td width="200"><b>Login</b></td>
            <td width="200"><b>Password</b></td>
            <td width="200"><b>Firstname</b></td>
            <td width="200"><b>Lastname</b></td>
            <td width="200"><b>Email</b></td>
            <td width="200"><b>Phone</b></td>
            <td width="200"><b>Company ID</b></td>
        </tr>
        <tr>
            <td width="200"><%=user.getLogin()%></td>
            <td width="200"><%=user.getPassword()%></td>
            <td width="200"><%=user.getFirstName()%></td>
            <td width="200"><%=user.getLastName()%></td>
            <td width="200"><%=user.getEMail()%></td>
            <td width="200"><%=user.getPhoneNumber()%></td>
            <td width="200"><%=user.getCompanyId()%></td>
        </tr>
    </table>

<BR><BR>

    <hr />
    <%
    if (session.getAttribute("userType").equals("transport")) {%>
    <h2>-=T=- transport profile buttons</h2>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" action="../agreementOverview"><button type="submit">Agreement overview</button></form></td>
            <td><form method="get" action="../vehiclereg"><button type="submit">Add new Vehicle</button></form></td>
            <td><form method="get" action="vehicleSearch.jsp"><button type="submit">Vehicle overview(create agreement)</button></form></td>
            <td><form method="get" action="../userReg"><button type="submit">Register new USER</button></form></td>
        </tr>
    </table>


    <%} else
    if (session.getAttribute("userType").equals("cargo")) {%>
    <h2>-=C=- cargo profile buttons</h2>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" action="../agreementOverview"><button type="submit">Agreement overview</button></form></td>
            <td><form method="get" action="../cargoReg"><button type="submit">Add new CARGO</button></form></td>
            <td><form method="get" action="cargoSearch.jsp"><button type="submit">CARGO overview(create agreement)</button></form></td>
            <td><form method="get" action="../userReg"><button type="submit">Register new USER</button></form></td>
        </tr>
    </table>


    <%} else { %>
    <h1>Sorry! Your company type is not -=C=-"cargo" and not -=T=-"transport"</h1>
    <%}%>
    <hr />



    <BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
    <h4><U>******** invisible BAR with all buttons. Lets pretend u dont see it ;) ******** invisible BAR with all buttons. Lets pretend u dont see it ;)</U></h4>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" action="../agreementOverview"><button type="submit">Agreement overview</button></form></td>
            <td><form method="get" action="../cargoReg"><button type="submit">Add new CARGO</button></form></td>
            <td><form method="get" action="../vehiclereg"><button type="submit">Add new Vehicle</button></form></td>
            <td><form method="get" action="cargoSearch.jsp"><button type="submit">CARGO overview(create agreement)</button></form></td>
            <td><form method="get" action="vehicleSearch.jsp"><button type="submit">Vehicle overview(create agreement)</button></form></td>
            <td><form method="get" action="../userReg"><button type="submit">Register new USER</button></form></td>
        </tr>
    </table>
    <h4><U>******** invisible BAR with all buttons. Lets pretend u dont see it ;) ******** invisible BAR with all buttons. Lets pretend u dont see it ;)</U></h4>


</div>
</body>
</html>

<html>
<head>
    <title>MVC</title>
</head>
</body>
</html>