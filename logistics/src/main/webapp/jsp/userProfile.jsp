<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page import="lv.javaguru.java2.domain.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.Cargo" %>
<%@ page import="lv.javaguru.java2.database.jdbc.CargoDAOImpl" %>
<%@ page import="lv.javaguru.java2.database.jdbc.VehicleDAOImpl" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%

    User user = (User)session.getAttribute("user");


    Map<String, Object> modelHashMap = (Map<String, Object>) request.getAttribute("model");
//    User user = (User) modelHashMap.get("user");
    List<Cargo> cargoList = (List<Cargo>) modelHashMap.get("cargoList");
    List<Vehicle> vehicleList = (List<Vehicle>) modelHashMap.get("vehicleList");




%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title><%=user.getFirstName()%>
        <%=user.getLastName()%> (<%=user.getLogin()%>) profile</title>
</head>
<body>
<div align="right"><U><b>Hello,
    <%=user.getFirstName()%>
    <%=user.getLastName()%> (<%=user.getLogin()%>)!
    -----[Type: <%=session.getAttribute("userType")%>]</b></U></div>
<div align="right"><a href="logout.jsp"><U>Exit</U></a></div>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
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





    <%--======================================--%>
    <%
    if (session.getAttribute("userType").equals("transport")) {%>
    <h2>-=T=- TRANSPORT PROFILE</h2>
    <div align="center"><img src="http://ts4.mm.bing.net/th?id=HN.608025893360110011&pid=15.1&H=109&W=160"/></div>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" target="_blank" action="agreementOverview"><button type="submit">Agreement overview</button></form></td>
            <td><form method="get" target="_blank" action="vehiclereg"><button type="submit">Add new Vehicle</button></form></td>
            <td><form method="get" target="_blank" action="jsp/cargoSearch.jsp"><button type="submit">CARGO overview(create agreement)</button></form></td>
            <td><form method="get" target="_blank" action="userReg"><button type="submit">Register new USER</button></form></td>
        </tr>
    </table>


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
            <td width="200"><%=vehicle.getUserId()%></td>
            <td>
                <input type="hidden" name="id" value="<%=vehicle.getVehicleId()%>">
                <input type="submit" target="_blank" name="action" value="Find 4 this vehicle">
            </td>

        </tr>
        </form>
        <%}%>
    </table>












    <%--======================================--%>
    <%} else
    if (session.getAttribute("userType").equals("cargo")) {%>
    <h2>-=C=- CARGO PROFILE</h2>
    <div align="center"><img src="http://knowin.files.wordpress.com/2010/12/gift-8.jpg"/></div>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" target="_blank" action="agreementOverview"><button type="submit">Agreement overview</button></form></td>
            <td><form method="get" target="_blank" action="cargoReg"><button type="submit">Add new CARGO</button></form></td>
            <td><form method="get" target="_blank" action="jsp/vehicleSearch.jsp"><button type="submit">Vehicle overview(create agreement)</button></form></td>
            <td><form method="get" target="_blank" action="userReg"><button type="submit">Register new USER</button></form></td>
        </tr>
    </table>

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
            <td width="50"><%=cargo.getUserId()%></td>
            <td>
                <input type="hidden" name="id" value="<%=cargo.getCargoId()%>">
                <input type="submit" name="action" value="Find 4 this cargo">
            </td>
         </tr>
        </form>
         <%}%>
         </table>







    <%--======================================--%>
    <%} else { %>
    <h1>-=N=- NO PROFILE</h1>
    <h1>Sorry! Your company type is not -=C=-"cargo" and not -=T=-"transport"</h1>
    <div align="center"><img src="http://nextchapternewlife.com/wp-content/uploads/2012/08/no-pic.jpg"/></div>
    <script language="Javascript">
        <!--
        alert ("Sorry! Your company type is not 'cargo' and not 'transport'. So no buttons to press for You :(")
        //-->
    </script>

    <%}%>
    <hr />



    <BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>    <BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
    <h4><U>******** invisible BAR with all buttons. Lets pretend u dont see it ;) ******** invisible BAR with all buttons. Lets pretend u dont see it ;)</U></h4>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" target="_blank" action="agreementOverview"><button type="submit">Agreement overview</button></form></td>
            <td><form method="get" target="_blank" action="cargoReg"><button type="submit">Add new CARGO</button></form></td>
            <td><form method="get" target="_blank" action="vehiclereg"><button type="submit">Add new Vehicle</button></form></td>
            <td><form method="get" target="_blank" action="jsp/cargoSearch.jsp"><button type="submit">CARGO overview(create agreement)</button></form></td>
            <td><form method="get" target="_blank" action="jsp/vehicleSearch.jsp"><button type="submit">Vehicle overview(create agreement)</button></form></td>
            <td><form method="get" target="_blank" action="userReg"><button type="submit">Register new USER</button></form></td>
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