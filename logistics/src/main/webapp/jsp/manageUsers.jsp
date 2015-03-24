<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.User" %>
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
    String title = (String) modelMap.get("title");
    List<User> users = (ArrayList<User>)modelMap.get("users");
    if(notification != null) {%>
    <p style="color:red"><b><I><%=notification%></I></b></p> <br>
    <%}%>
<h2><b><U><%=title%></U></b></h2>
    <table align="center" border="1" width="80%" class="sortable">
        <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Company</th>
                <th>[Edit]</th>
                <th>[Delete]</th>
            </tr>
        </thead>
        <tbody>
        <%for (User user : users) {%>
            <tr>
                <td><%=user.getUserId()%></td>
                <td><%=user.getLogin()%></td>
                <td><%=user.getFirstName()%></td>
                <td><%=user.getLastName()%></td>
                <td><%=user.getEMail()%></td>
                <td><%=user.getPhoneNumber()%></td>
                <td><%=user.getCompany().getName()%></td>
                <form method="post" action="userEdit">
                    <td width="20">
                        <input type="hidden" name="userId" value="<%=user.getUserId()%>">
                        <input type="submit" name="action" value="Edit">
                    </td>
                </form>
                <form method="post" action="userDelete">
                    <td width="20">
                        <input type="hidden" name="userId" value="<%=user.getUserId()%>">
                        <input type="submit" name="action" value="Delete">
                    </td>
                </form>
            </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>