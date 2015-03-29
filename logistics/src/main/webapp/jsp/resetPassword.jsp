<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.Value" %>
<!DOCTYPE html>
<%
    User user = (User)session.getAttribute("user");

    Map<String, Object> modelHashMap = (Map<String, Object>) request.getAttribute("model");
    String info = (String)modelHashMap.get("info");
    String color = (String)modelHashMap.get("color");

    System.out.println(request.getMethod());

    System.out.println("=======================================    "+info);


%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>reset password</title>
</head>
<body>
<BR><BR>
<div align="center">
    <form method="post" action="resetPassword">
        old password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="password" name="oldpassword" /><br/>
        <br/>
        new password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="newpassword1" /><br/>
        <br/>
        confirm password:&nbsp;&nbsp;&nbsp;<input type="text" name="newpassword2" /><br/>
        <br/>
        <input type="hidden" name="isPasswordPage" value="Yes">
        <input type="hidden" name="login" value=<%=user.getLogin()%>>
        <br/>
        <input type="submit" value="reset" />
    </form>


    <%
   if(info != null) {%>
    <div align="center">
    <font color=<%=color%>><br><%=info%></font><br>
    </div>
   <%}%>

</div>
</body>
</html>
