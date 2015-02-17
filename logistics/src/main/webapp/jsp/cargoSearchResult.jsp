<%@ page import="lv.javaguru.java2.servlet.mvc.MVCModel" %>
<%@ page import="lv.javaguru.java2.domain.Cargo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Search result</title>
</head>
<body>
<div align="center"><img src="../images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="../index.html">BACK</a></div>
<hr />
<div align="center">
    <table align="center" border="1" width="450">
        <tr>
            <td width="50"><b>Id</b></td>
            <td width="100"><b>Weight</b></td>
            <td width="100"><b>Vehicle Type</b></td>
            <td width="100"><b>Status</b></td>
            <td width="100"><b>Load Date</b></td>
            <td width="100"><b>Unload Date</b></td>
            <td width="100"><b>Load Address</b></td>
            <td width="100"><b>Unload Address</b></td>
        </tr>
        <%
            List<Cargo> cargoList = (ArrayList<Cargo>)request.getAttribute("model");
            for (int i = 0; i < cargoList.size(); i ++) {
                Cargo cargo = cargoList.get(i);
        %>
        <tr>
            <td width="50"><%=cargo.getCargoId()%></td>
            <td width="100"><%=cargo.getWeight()%></td>
            <td width="100"><%=cargo.getVehicleType()%></td>
            <td width="100"><%=cargo.getStatus()%></td>
            <td width="100"><%=cargo.getLoadDate()%></td>
            <td width="100"><%=cargo.getUnloadDate()%></td>
            <td width="100"><%=cargo.getLoadAddress()%></td>
            <td width="100"><%=cargo.getUnloadAddress()%></td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>

