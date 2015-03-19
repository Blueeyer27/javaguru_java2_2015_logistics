<%@ page import="lv.javaguru.java2.domain.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>-= CARGO SYSTEM=-</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right" style="background-image:url(.jpg);border: 3px dotted grey;position:absolute;top:110;left:100;height:120;width:140;">
    <% User user = (User)session.getAttribute("user"); %>
    <table align="center" border="0" width="260">
        <tr align="center">
            <%if (user == null) {%>
                <td><form method="get" action="jsp/login.jsp"><button type="submit">Login</button></form></td>
            <%} else {%>
                <td><form method="get" action="userLogin"><button type="submit">My Profile</button></form></td>
                <td><form method="get" action="jsp/logout.jsp"><button type="submit">Exit</button></form></td>
                <td><form method="get" target="_blank" action="userReg"><button type="submit">Register new</button></form></td>
            <%}%>
        </tr>
    </table>
</div>
<hr />
<br/><br/><br/>
<div align="left"><a href="companyRegPage">Add new COMPANY</a></div>
<div align="left">.</div>
<div align="left"><a href="getallcompanywithuserlist">G-E-T CompanyList joined with UserList!(strategy EAGER works only = @OneToMany (fetch=FetchType.EAGER)
    )</a></div>
<div align="left">.</div>
<BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
<div align="left"><U>Lets hide these options below?</U></div>
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
</body>
</html>