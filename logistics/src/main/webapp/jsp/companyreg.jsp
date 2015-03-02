<%@ page import="lv.javaguru.java2.domain.Company" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>New Company created!</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right" ><a href="index.html">BACK</a></div>
<hr />
<div align="center">
    <table align="center" border="3" width="700">
        <tr>
            <td width="600"><b>New company created!</b></td>
        <tr>
        </tr>
    </table>

    <BR>
    <BR>

    <table align="center" border="1" width="700">
        <tr>
            <td width="500"><b>company data:</b></td>
        </tr>
        <tr>
            <td width="200" ><b>Name</b></td>
            <td width="200"><b>Reg Number</b></td>
            <td width="200"><b>Reg Address</b></td>
            <td width="200"><b>Actual Address</b></td>
            <td width="200"><b>Bank name</b></td>
            <td width="200"><b>Bank IBAN nr</b></td>
            <td width="200"><b>Country</b></td>
            <td width="200"><b>Company Type</b></td>
        </tr>
        <%
            Company company = (Company)request.getAttribute("model");
        %>
        <tr>
            <td width="200"><%=company.getName()%></td>
            <td width="200"><%=company.getRegNumber()%></td>
            <td width="200"><%=company.getRegAddress()%></td>
            <td width="200"><%=company.getActualAddress()%></td>
            <td width="200"><%=company.getBank()%></td>
            <td width="200"><%=company.getIban()%></td>
            <td width="200"><%=company.getCountry()%></td>
            <td width="200"><%=company.getType()%></td>
        </tr>
    </table>
</div>
</body>
</html>


<html>
<head>
    <title>MVC</title>
</head>
</body>
</html>