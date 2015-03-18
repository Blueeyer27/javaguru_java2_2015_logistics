<%@ page import="lv.javaguru.java2.domain.Cargo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.Vehicle" %>
<%@ page import="lv.javaguru.java2.domain.Agreement" %>
<%@ page import="lv.javaguru.java2.database.jdbc.CargoDAOImpl" %>
<%@ page import="lv.javaguru.java2.database.jdbc.VehicleDAOImpl" %>
<%@ page import="lv.javaguru.java2.database.VehicleDAO" %>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Agreement overview</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<div align="center">
    <%
        List<Agreement> agreementList = (ArrayList<Agreement>)request.getAttribute("model");
//        CargoDAOImpl cargoDAO = new CargoDAOImpl();
//        VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
    %>
    <h2><b><U>Agreements overview list:</U></b></h2>
    <table align="center" border="1" width="80%">
    <tr>
        <td width="200"><b>ID</b></td>
        <td width="200"><b>Cargo ID</b></td>
        <td width="200"><b>Vehicle ID</b></td>
        <td width="200"><b>Status</b></td>
        <td width="70"><b>Decision</b></td>
        <td width="130"><b>Press it!</b></td>
    </tr>
    </table>

    <%
    for (Agreement agreement : agreementList) {
//        Cargo cargo =  cargoDAO.getById(agreement.getAgreementId());
//        Vehicle vehicle = vehicleDAO.getById(agreement.getAgreementId());

    %>
        <HR>
        <BR>
        <table align="center" border="1" width="80%">
<%--
            <tr>
                <td width="200"><b>ID</b></td>
                <td width="200"><b>Cargo ID</b></td>
                <td width="200"><b>Vehicle ID</b></td>
                <td width="200"><b>Status</b></td>
                <td width="70"><b>Decision</b></td>
                <td width="130"><b>Press it!</b></td>
            </tr>
--%>
        <form method="post" action="processAgreement">
        <tr>
            <td width="200"><%=agreement.getAgreementId()%></td>
            <td width="200"><%=agreement.getCargo().getCargoId()%></td>
            <td width="200"><%=agreement.getVehicle().getVehicleId()%></td>
            <td width="200"><%=agreement.getStatus()%></td>
            <td width="20">
               <select name="processType">
               <option value="accept" selected>&nbsp;Accept
               <option value="cancel" >&nbsp;Cancel
               </select><br/>
            </td>
            <td width="20">
                <input type="hidden" name="agreementId" value="<%=agreement.getAgreementId()%>">
                <input type="hidden" name="cargoId" value="<%=agreement.getCargo().getCargoId()%>">
                <input type="hidden" name="vehicleId" value="<%=agreement.getVehicle().getVehicleId()%>">
                <input type="submit" name="action" value="Process Agreement">
            </td>
            </tr>
        </form>
        </table>






        <%
//            if (cargo != null && vehicle != null) {
        %>
            <U>Agreement details:</U>
            <table align="center" border="1" width="80%">
            <tr>
                <%--<td width="200">Cargo type=<%=cargo.getVehicleType()%></td>--%>
                <%--<td width="200">Cargo weight=<%=cargo.getWeight()%></td>--%>
                <%--<td width="200">Load date=<%=cargo.getLoadDate()%></td>--%>
                <%--<td width="200">Unload date=<%=cargo.getUnloadDate()%></td>--%>
                <%--<td width="200">Cargo status=<%=cargo.getStatus()%></td>--%>

                <td width="200">Cargo type=<%=agreement.getCargo().getVehicleType()%></td>
                <td width="200">Cargo weight=<%=agreement.getCargo().getWeight()%></td>
                <td width="200">Load date=<%=agreement.getCargo().getLoadDate()%></td>
                <td width="200">Unload date=<%=agreement.getCargo().getUnloadDate()%></td>
                <td width="200">Cargo status=<%=agreement.getCargo().getStatus()%></td>
            </tr>
            <tr>
                <%--<td width="200">Vehicle name=<%=vehicle.getName()%></td>--%>
                <%--<td width="200">Vehicle capacity=<%=vehicle.getCapacity()%></td>--%>
                <%--<td width="200">Vehicle plate=<%=vehicle.getplateNumber()%></td>--%>
                <%--<td width="200">Vehicle trailer=<%=vehicle.gettrailerNumber()%></td>--%>
                <%--<td width="200">Vehicle status=<%=vehicle.getStatus()%></td>--%>

                <td width="200">Vehicle name=<%=agreement.getVehicle().getName()%></td>
                <td width="200">Vehicle capacity=<%=agreement.getVehicle().getCapacity()%></td>
                <td width="200">Vehicle plate=<%=agreement.getVehicle().getplateNumber()%></td>
                <td width="200">Vehicle trailer=<%=agreement.getVehicle().gettrailerNumber()%></td>
                <td width="200">Vehicle status=<%=agreement.getVehicle().getStatus()%></td>
            </tr>
            <%--<%}%>--%>
            </table>
        <BR>
    <%}
    if (agreementList.size()==0) {
    %>
    <script language="Javascript">
        <!--
        alert ("Sorry, NO AGREEMENTS FOUND in DB! :(")
        //-->
    </script>
    <%}%>
</div>
</body>
</html>

