<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.Company" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="sorttable.js"></script>
    <title>Manage companies</title>
</head>
<body>
<jsp:include page="adminHeader.jsp" />
<div align="center">
<%
    Map<String, Object> modelMap = (Map<String, Object>) request.getAttribute("model");
    String notification = (String) modelMap.get("notification");
    List<Company> allCompanies = (ArrayList<Company>)modelMap.get("allCompanies");
    if(notification != null) {%>
    <p style="color:red"><b><I><%=notification%></I></b></p> <br>
    <%}%>
<h2><b><U>Companies:</U></b></h2>
    <table align="center" border="1" width="80%" class="sortable">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Reg. number</th>
                <th>Reg. address</th>
                <th>Actual address</th>
                <th>Bank</th>
                <th>IBAN</th>
                <th>Country</th>
                <th>Type</th>
                <th>[Edit]</th>
                <th>[Delete]</th>
                <th>[Manage Users]</th>
            </tr>
        </thead>
        <tbody>
        <%for (Company company : allCompanies) {%>
            <tr>
                <td><%=company.getCompanyId()%></td>
                <td><%=company.getName()%></td>
                <td><%=company.getRegNumber()%></td>
                <td><%=company.getRegAddress()%></td>
                <td><%=company.getActualAddress()%></td>
                <td><%=company.getBank()%></td>
                <td><%=company.getIban()%></td>
                <td><%=company.getCountry()%></td>
                <td><%=company.getType()%></td>
                <form method="post" action="companyEdit">
                    <td width="20">
                        <input type="hidden" name="companyId" value="<%=company.getCompanyId()%>">
                        <input type="submit" name="action" value="Edit">
                    </td>
                </form>
                <form method="post" action="companyDelete">
                    <td width="20">
                        <input type="hidden" name="companyId" value="<%=company.getCompanyId()%>">
                        <input type="submit" name="action" value="Delete">
                    </td>
                </form>
                <form method="post" action="manageUsers">
                    <td width="20">
                        <input type="hidden" name="companyId" value="<%=company.getCompanyId()%>">
                        <input type="submit" name="action" value="Manage Users">
                    </td>
                </form>
            </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>