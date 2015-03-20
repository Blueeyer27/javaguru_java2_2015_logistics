<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.Cargo" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
    User user = (User)session.getAttribute("user");
    Map<String, Object> modelHashMap = (Map<String, Object>) request.getAttribute("model");
    List<Cargo> cargoList = (List<Cargo>) modelHashMap.get("cargoList");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title><%=user.getFirstName()%>
        <%=user.getLastName()%> (<%=user.getLogin()%>) profile</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div align="center">
    <table align="center" border="1" width="90%">
        <tr>
            <td width="400"><b>Hi, :) <%=user.getFirstName() + " " + user.getLastName()%> </b></td>
        <tr>
        <tr>
            <td width="400"><b>USER-DATA:</b></td>
        <tr>
        <tr>
            <td width="200"><b>Login</b></td>
            <td width="200"><b>Firstname</b></td>
            <td width="200"><b>Lastname</b></td>
            <td width="200"><b>Email</b></td>
            <td width="200"><b>Phone</b></td>
            <td width="200"><b>Company</b></td>
        </tr>
        <tr>
            <td width="200"><%=user.getLogin()%></td>
            <td width="200"><%=user.getFirstName()%></td>
            <td width="200"><%=user.getLastName()%></td>
            <td width="200"><%=user.getEMail()%></td>
            <td width="200"><%=user.getPhoneNumber()%></td>
            <td width="200"><%=user.getCompany().getName()%></td>
        </tr>
    </table>

<BR><BR>

    <h2>-=C=- CARGO PROFILE</h2>
    <div align="center"><img src="images/cargo.jpg"/></div>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" target="_blank" action="agreementOverview"><button type="submit">Agreement overview</button></form></td>
            <td><form method="get" target="_blank" action="cargoReg"><button type="submit">Add new CARGO</button></form></td>
            <td><form method="get" target="_blank" action="jsp/vehicleSearch.jsp"><button type="submit">Vehicle overview(create agreement)</button></form></td>
            <td><form method="get" target="_blank" action="userReg"><button type="submit">Register new USER</button></form></td>
        </tr>
    </table>
    <h2>Availible cargoes:</h2>
    <hr />
    <table align="center" border="1" width="90%">
        <tr>
            <td width="50"><b>Id</b></td>
            <td width="200"><b>Cargo veh. Type</b></td>
            <td width="200"><b>Weight</b></td>
            <td width="200"><b>Load Address</b></td>
            <td width="200"><b>Unload Address</b></td>
            <td width="200"><b>Load Date</b></td>
            <td width="200"><b>Unload Date</b></td>
            <td width="200"><b>Status</b></td>
            <td width="200"><b>User ID</b></td>
            <td width="200"><b>Find vehicle</b></td>
        </tr>
        <%
        for (int i = 0; i < cargoList.size(); i ++) {
            Cargo cargo = cargoList.get(i);
        %>
        <form method="post" action="sendRequestCargo">
        <tr>
            <td width="50"><%=cargo.getCargoId()%></td>
            <td width="50"><%=cargo.getVehicleType()%></td>
            <td width="50"><%=cargo.getWeight()%></td>
            <td width="50"><%=cargo.getLoadAddress()%></td>
            <td width="50"><%=cargo.getUnloadAddress()%></td>
            <td width="50"><%=cargo.getLoadDate()%></td>
            <td width="50"><%=cargo.getUnloadDate()%></td>
            <td width="50"><%=cargo.getStatus()%></td>
            <td width="50"><%=cargo.getUser().getUserId()%></td>
            <td>
                <input type="hidden" name="id" value="<%=cargo.getCargoId()%>">
                <input type="submit" name="action" value="Find 4 this cargo">
            </td>
         </tr>
        </form>
         <%}%>
         </table>
    <hr />

    <BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>    <BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
    <h4><U> ********** admin bar with all buttons. (must be putted somewhere) ********** admin bar with all buttons. (must be putted somewhere) ********** </U></h4>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" target="_blank" action="companyRegPage"><button type="submit">Add new COMPANY</button></form></td>
            <td><form method="get" target="_blank" action="userReg"><button type="submit">Register new USER</button></form></td>
            <td><form method="get" target="_blank" action="jsp/vehicles.jsp"><button type="submit">Vehicles</button></form></td>
            <td><form method="get" target="_blank" action="agreementOverview"><button type="submit">Agreement overview</button></form></td>
            <td><form method="get" target="_blank" action="cargoReg"><button type="submit">Add new CARGO</button></form></td>
            <td><form method="get" target="_blank" action="vehicleReg"><button type="submit">Add new VEHICLE</button></form></td>
            <td><form method="get" target="_blank" action="jsp/cargoSearch.jsp"><button type="submit">CARGO overview(create agreement)</button></form></td>
            <td><form method="get" target="_blank" action="jsp/vehicleSearch.jsp"><button type="submit">Vehicle overview(create agreement)</button></form></td>
        </tr>
    </table>
    <h4><U> ********** admin bar with all buttons. (must be putted somewhere) ********** admin bar with all buttons. (must be putted somewhere) ********** </U></h4>


</div>
</body>
</html>

<html>
<head>
    <title>MVC</title>
</head>
</body>
</html>