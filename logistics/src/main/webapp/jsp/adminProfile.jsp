<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language="JavaScript">
    function formop()
    {
        form = window.open("resetPassword", "name", "tollbar=no, menubar=no, fullscreen=no, scrollbars=yes, top=120, left=150, width=400, height=300");
    }
</script>
<div align="right"><a href="javascript:formop();" target="" style="text-decoration:none"><U>Reset password</U></a></div>
<%--<div align="left"><a href="resetPassword" target="_blank" style="text-decoration:none"><U>Reset password</U></a></div>--%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Admin login page</title>
</head>
<body>
<jsp:include page="adminHeader.jsp" />
<div align="center">

    <h2>-=A=- ADMIN PROFILE</h2>
    <div align="center"><img src="images/admin.jpg"/></div>

    <h2>Insert new record into database:</h2>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" target="_blank" action="companyRegPage"><button type="submit">New Company</button></form></td>
            <td><form method="get" target="_blank" action="userReg"><button type="submit">New User</button></form></td>
            <td><form method="get" target="_blank" action="cargoReg"><button type="submit">New Cargo</button></form></td>
            <td><form method="get" target="_blank" action="vehicleReg"><button type="submit">New Vehicle</button></form></td>
        </tr>
    </table>

    <h2>Manage existing records:</h2>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" action="manageCompanies"><button type="submit">Manage All Companies</button></form></td>
            <td><form method="get" action="manageUsers"><button type="submit">Manage All Users</button></form></td>
        </tr>
    </table>

    <BR>
    <h2>temporary:</h2>
    <table align="center" border="1" width="90%">
        <tr align="center">
            <td><form method="get" target="_blank" action="jsp/vehicles.jsp"><button type="submit">Vehicles</button></form></td>
            <td><form method="get" target="_blank" action="agreementOverview"><button type="submit">Agreement overview</button></form></td>
            <td><form method="get" target="_blank" action="jsp/cargoSearch.jsp"><button type="submit">CARGO overview(create agreement)</button></form></td>
            <td><form method="get" target="_blank" action="jsp/vehicleSearch.jsp"><button type="submit">Vehicle overview(create agreement)</button></form></td>
        </tr>
    </table>
    <BR><BR>
    <div align="left"><a href="companyRegPage">Add new COMPANY</a></div>
    <div align="left">.</div>
    <div align="left"><a href="userReg">Register new USER</a></div>
    <div align="left">.</div>
    <div align="left"><a href="jsp/vehicles.jsp">VEHICLES</a></div>
    <div align="left">.</div>
    <div align="left"><a href="cargoReg">Add new CARGO</a></div>
    <div align="left">.</div>
    <div align="left"><a href="vehicleReg">Add new VEHICLE</a></div>
    <div align="left">.</div>
    <div align="left">.</div>
    <div align="left"><a href="jsp/cargoSearch.jsp">FIND CARGO &nbsp;&nbsp;&nbsp;-- (agreement wizard)</a></div>
    <div align="left">.</div>
    <div align="left"><a href="jsp/vehicleSearch.jsp">FIND VEHICLE -- (agreement wizard)</a></div>
    <div align="left">.</div>
    <div align="left"><a href="agreementOverview">Agreement overview</a></div>
    <div align="left">.</div>

</div>
</body>
</html>