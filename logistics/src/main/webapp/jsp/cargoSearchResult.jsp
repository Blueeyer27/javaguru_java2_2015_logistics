<%@ page import="lv.javaguru.java2.domain.Cargo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Search result</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="../java2">Back to home</a></div>
<hr />
<div align="center">

    <table align="center" border="1" width="950">
        <tr>
            <td width="50"><b>Id</b></td>
            <td width="100"><b>Weight</b></td>
            <td width="100"><b>Vehicle Type</b></td>
            <td width="100"><b>Status</b></td>
            <td width="200"><b>Load Date</b></td>
            <td width="200"><b>Unload Date</b></td>
            <td width="200"><b>Load Address</b></td>
            <td width="200"><b>Unload Address</b></td>
        </tr>
        <%
            List<Cargo> cargoList = (ArrayList<Cargo>)request.getAttribute("model");
            for (int i = 0; i < cargoList.size(); i ++) {
                Cargo cargo = cargoList.get(i);
        %>
        <form method="post" action="sendRequestCargo">
            <tr>
                <td width="50"><%=cargo.getCargoId()%></td>
                <td width="100"><%=cargo.getWeight()%></td>
                <td width="100"><%=cargo.getVehicleType()%></td>
                <td width="100"><%=cargo.getStatus()%></td>
                <td width="200"><%=cargo.getLoadDate()%></td>
                <td width="200"><%=cargo.getUnloadDate()%></td>
                <td width="200"><%=cargo.getLoadAddress()%></td>
                <td width="200"><%=cargo.getUnloadAddress()%></td>
                <td>
                    <input type="hidden" name="id" value="<%=cargo.getCargoId()%>">
                    <input type="submit" name="action" value="Send Request">
                </td>
            </tr>
        </form>
        <%}
        if (cargoList.size()==0) {
        %>
        <script language="Javascript">
            <!--
            alert ("Sorry, NO CARGOS FOUND in DB by Your criteria! :(")
            //-->
        </script>

        <%}%>

    </table>
</div>
</body>
</html>

