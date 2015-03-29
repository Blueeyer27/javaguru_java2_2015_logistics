<%@ page import="lv.javaguru.java2.domain.User" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="style.css">
  <title>Edit my data:</title>
</head>
<jsp:include page="adminHeader.jsp" />
<div align="center">
<%
    User user = (User)session.getAttribute("user");
%>
    <form method="post" action="userUpdate">
        Login:      <input type="text" name="login" value="<%=user.getLogin()%>" /><br/><br/>
        Name:       <input type="text" name="name"  value="<%=user.getFirstName()%>"/><br/><br/>
        Surname:    <input type="text" name="surname"  value="<%=user.getLastName()%>"/><br/><br/>
        Email:      <input type="text" name="eMail"  value="<%=user.getEMail()%>"/><br/><br/>
        Phone:      <input type="text" name="phoneNumber"  value="<%=user.getPhoneNumber()%>"/><br/><br/>
        <input type="submit" value="Save">
    </form>

    <BR><BR>
    
    <form method="post" action="userUpdate">
        new Password:      <input type="text" name="login" value="<%=user.getLogin()%>"/><br/><br/>
        Name:       <input type="text" name="name"  value="<%=user.getFirstName()%>"/><br/><br/>
        <input type="submit" value="Reset password">
    </form>

</div>
</body>
</html>
