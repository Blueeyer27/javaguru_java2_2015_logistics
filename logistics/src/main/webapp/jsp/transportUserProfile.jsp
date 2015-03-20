<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page import="lv.javaguru.java2.domain.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
    User user = (User)session.getAttribute("user");
    Map<String, Object> modelHashMap = (Map<String, Object>) request.getAttribute("model");
    List<Vehicle> vehicleList = (List<Vehicle>) modelHashMap.get("vehicleList");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title><%=user.getFirstName()%>
        <%=user.getLastName()%> (<%=user.getLogin()%>) profile</title>
</head>
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

    <hr />
    <h2>-=T=- TRANSPORT PROFILE</h2>
    <div align="center"><img src="images/vehicle.jpg"/></div>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" target="_blank" action="agreementOverview"><button type="submit">Agreement overview</button></form></td>
            <td><form method="get" target="_blank" action="vehicleReg"><button type="submit">Add new VEHICLE</button></form></td>
            <td><form method="get" target="_blank" action="jsp/cargoSearch.jsp"><button type="submit">CARGO overview(create agreement)</button></form></td>
            <td><form method="get" target="_blank" action="userReg"><button type="submit">Register new USER</button></form></td>
            <td><form method="get" target="_blank" action="manageVehicles"><button type="submit">Manage Other Vehicles</button></form></td>
        </tr>
    </table>
    <h2>Availible vehicles:</h2>
    <hr />
    <table align="center" border="1" width="90%">
        <tr>
            <td width="50"><b>Id</b></td>
            <td width="200"><b>Name</b></td>
            <td width="200"><b>Type</b></td>
            <td width="200"><b>PlateNumber</b></td>
            <td width="200"><b>Trailer Number</b></td>
            <td width="200"><b>Capacity</b></td>
            <td width="200"><b>Current Status</b></td>
            <td width="200"><b>User ID</b></td>
            <td width="200"><b>Find cargo</b></td>
        </tr>

        <%
        for (int i = 0; i < vehicleList.size(); i ++) {
            Vehicle vehicle = vehicleList.get(i);
        %>
        <form method="post" action="sendRequestVehicle">
        <tr>
            <td width="50"><%=vehicle.getVehicleId()%></td>
            <td width="200"><%=vehicle.getName()%></td>
            <td width="200"><%=vehicle.getType()%></td>
            <td width="200"><%=vehicle.getplateNumber()%></td>
            <td width="200"><%=vehicle.gettrailerNumber()%></td>
            <td width="200"><%=vehicle.getCapacity()%></td>
            <td width="200"><%=vehicle.getStatus()%></td>
            <td width="200"><%=vehicle.getUser().getUserId()%></td>
            <td>
                <input type="hidden" name="id" value="<%=vehicle.getVehicleId()%>">
                <input type="submit" target="_blank" name="action" value="Find 4 this vehicle">
            </td>

        </tr>
        </form>
        <%}%>
    </table>
    <hr />

    <BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>    <BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
    <h4><U> ********** admin bar with all buttons. (must be putted somewhere) ********** admin bar with all buttons. (must be putted somewhere) ********** </U>></h4>
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
    <h4><U> ********** admin bar with all buttons. (must be putted somewhere) ********** admin bar with all buttons. (must be putted somewhere) ********** </U>></h4>

</div>
</body>
</html>

<html>
<head>
    <title>MVC</title>
</head>
</body>
</html>