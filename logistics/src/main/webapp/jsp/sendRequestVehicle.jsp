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
        Map<String, Object> modelHashMap = (Map<String, Object>) request.getAttribute("model");
        Vehicle vehicle = (Vehicle) modelHashMap.get("vehicle");
        List<Cargo> cargoList = (List<Cargo>) modelHashMap.get("cargoList");
    %>
    <h2>Choosen Vehicle id = <%=vehicle.getVehicleId()%>
        (Vehicle Type = <%=vehicle.getType()%>) (Vehicle Capacity = <%=vehicle.getCapacity()%>) </h2>
    <h2><font color="red" ><b>Important!  Cargo serch result by TYPE = <%=vehicle.getType()%> and WEIGHT <=  <%=vehicle.getCapacity()%>.</b></font>
    <BR><b>Please choose cargo from the list:</b></h2>
    <table align="center" border="1" width="950">
        <tr>
            <td width="50"><b>Id</b></td>
            <td width="200"><b>Vehicle Type</b></td>
            <td width="200"><b>Weight</b></td>
            <td width="200"><b>Load Address</b></td>
            <td width="200"><b>Unload Address</b></td>
            <td width="200"><b>Load Date</b></td>
            <td width="200"><b>Unload Date</b></td>
            <td width="200"><b>Status</b></td>
            <td width="200"><b>User ID</b></td>
        </tr>
        <%
            for (int i = 0; i < cargoList.size(); i ++) {
                Cargo cargo = cargoList.get(i);
        %>
        <form method="post" action="createAgreement">
            <tr>
                <td width="50"><%=cargo.getCargoId()%></td>
                <td width="200"><%=cargo.getVehicleType()%></td>
                <td width="200"><%=cargo.getWeight()%></td>
                <td width="200"><%=cargo.getLoadAddress()%></td>
                <td width="200"><%=cargo.getUnloadAddress()%></td>
                <td width="200"><%=cargo.getLoadDate()%></td>
                <td width="200"><%=cargo.getUnloadDate()%></td>
                <td width="200"><%=cargo.getStatus()%></td>
                <td width="200"><%=cargo.getUserId()%></td>
                <td>
                    <input type="hidden" name="cargoId" value="<%=cargo.getCargoId()%>">
                    <input type="hidden" name="vehicleId" value="<%=vehicle.getVehicleId()%>">
                    <input type="submit" name="action" value="Create Agreement">
                </td>
            </tr>
        </form>
        <%}

        if (cargoList.size()==0) {
        %>
        <script language="Javascript">
            <!--
            alert ("Sorry, NO CARGOES FOUND in DB! (by Your  VEHICLE criteria) :(")
            //-->
        </script>
        <%}%>

    </table>
</div>
</body>
</html>

