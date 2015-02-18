<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="lv.javaguru.java2.domain.Vehicle" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Vehicle Search result</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<div align="center">
    <table align="center" border="1" width="450">
        <tr>
            <td width="50"><b>Id</b></td>
            <td width="200"><b>Vehicle Name</b></td>
            <td width="200"><b>Vehicle Type</b></td>
            <td width="200"><b>Capacity</b></td>
            <td width="200"><b>Status</b></td>
            <td width="200"><b>Plate number</b></td>
            <td width="200"><b>Trailer number</b></td>
        </tr>
        <%
            List<Vehicle> vehicleList = (ArrayList<Vehicle>)request.getAttribute("model");
            for (int i = 0; i < vehicleList.size(); i ++) {
                Vehicle vehicle = vehicleList.get(i);
        %>
        <tr>
            <td width="50"><%=vehicle.getVehicleId()%></td>
            <td width="200"><%=vehicle.getName()%></td>
            <td width="200"><%=vehicle.getType()%></td>
            <td width="200"><%=vehicle.getCapacity()%></td>
            <td width="200"><%=vehicle.getStatus()%></td>
            <td width="200"><%=vehicle.getplateNumber()%></td>
            <td width="200"><%=vehicle.gettrailerNumber()%></td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>

