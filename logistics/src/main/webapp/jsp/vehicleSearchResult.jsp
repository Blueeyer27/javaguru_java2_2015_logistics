<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="lv.javaguru.java2.domain.Vehicle" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Vehicle Search result</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div align="center">
    <table align="center" border="1" width="950">
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
        <form method="post" action="sendRequestVehicle">
        <tr>
            <td width="50"><%=vehicle.getVehicleId()%></td>
            <td width="200"><%=vehicle.getName()%></td>
            <td width="200"><%=vehicle.getType()%></td>
            <td width="200"><%=vehicle.getCapacity()%></td>
            <td width="200"><%=vehicle.getStatus()%></td>
            <td width="200"><%=vehicle.getplateNumber()%></td>
            <td width="200"><%=vehicle.gettrailerNumber()%></td>
            <td>
                <input type="hidden" name="id" value="<%=vehicle.getVehicleId()%>">
                <input type="submit" name="action" value="Send Request">
            </td>
        </tr>
        </form>
        <%}

        if (vehicleList.size()==0) {
        %>
        <script language="Javascript">
            <!--
            alert ("Sorry, NO VEHICLES FOUND in DB by Your criteria! :(")
            //-->
        </script>
        <%}%>

    </table>
</div>
</body>
</html>

