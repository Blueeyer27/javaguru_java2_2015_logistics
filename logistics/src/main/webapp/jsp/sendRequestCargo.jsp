<%@ page import="lv.javaguru.java2.domain.Cargo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.Vehicle" %>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Search result</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<div align="center">
    <%
        Map<String, Object> modelMap = (Map<String, Object>) request.getAttribute("model");
        Cargo cargo = (Cargo) modelMap.get("cargo");
        List<Vehicle> vehicleList = (List<Vehicle>) modelMap.get("vehicleList");
    %>
    <h1>Choosen Cargo id = <%=cargo.getCargoId()%>
        (Vehicle Type = <%=cargo.getVehicleType()%>)</h1>
    <b>Please choose vehicle(type = <%=cargo.getVehicleType()%>) from list:</b>
    <table align="center" border="1" width="750">
        <tr>
            <td width="50"><b>Id</b></td>
            <td width="200"><b>Name</b></td>
            <td width="200"><b>PlateNumber</b></td>
            <td width="200"><b>Trailer Number</b></td>
            <td width="200"><b>Capacity</b></td>
            <td width="200"><b>Current Status</b></td>
        </tr>
        <%
            for (int i = 0; i < vehicleList.size(); i ++) {
                Vehicle vehicle = vehicleList.get(i);
        %>
        <form method="post" action="createAgreement">
            <tr>
                <td width="50"><%=vehicle.getVehicleId()%></td>
                <td width="200"><%=vehicle.getName()%></td>
                <td width="200"><%=vehicle.getplateNumber()%></td>
                <td width="200"><%=vehicle.gettrailerNumber()%></td>
                <td width="200"><%=vehicle.getCapacity()%></td>
                <td width="200"><%=vehicle.getStatus()%></td>
                <td>
                    <input type="hidden" name="cargoId" value="<%=cargo.getCargoId()%>">
                    <input type="hidden" name="vehicleId" value="<%=vehicle.getVehicleId()%>">
                    <input type="submit" name="action" value="Create Agreement">
                </td>
            </tr>
        </form>
        <%}%>
    </table>
</div>
</body>
</html>
