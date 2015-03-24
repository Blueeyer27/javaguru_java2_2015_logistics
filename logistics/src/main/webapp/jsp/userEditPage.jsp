<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.Company" %>
<%@ page import="lv.javaguru.java2.domain.User" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="style.css">
  <title>Edit user info:</title>
</head>
<jsp:include page="adminHeader.jsp" />
<div align="center">
<%
    Map<String, Object> modelMap = (Map<String, Object>) request.getAttribute("model");
    User user = (User) modelMap.get("user");
    List<Company> companies = (List<Company>) modelMap.get("companies");
%>
  <form method="post" action="userUpdate">
    <input type="hidden" name="userId" value="<%=user.getUserId()%>">
    Login:      <input type="text" name="login" value="<%=user.getLogin()%>" /><br/><br/>
    Name:       <input type="text" name="name"  value="<%=user.getFirstName()%>"/><br/><br/>
    Surname:    <input type="text" name="surname"  value="<%=user.getLastName()%>"/><br/><br/>
    Email:      <input type="text" name="eMail"  value="<%=user.getEMail()%>"/><br/><br/>
    Phone:      <input type="text" name="phoneNumber"  value="<%=user.getPhoneNumber()%>"/><br/><br/>
    Company:
    <select name="company">
      <option value=<%=user.getCompany().getCompanyId()%> selected="true" style="display:none;"><%=user.getCompany().getName()%></option>
      <%for (Company company : companies) {%>
      <option value=<%=company.getCompanyId()%>><%=company.getName()%></option>
      <%}%>
    </select><br/><br/>
    <input type="submit" value="Update">
    <input type="reset" value="Reset changes">
  </form>
    <form action="manageUsers">
        <input type="submit" value="Cancel">
    </form>
</div>
</body>
</html>
