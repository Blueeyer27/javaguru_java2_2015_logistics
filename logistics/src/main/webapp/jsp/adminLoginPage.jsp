<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Admin login page</title>
</head>
<body>
<jsp:include page="adminHeader.jsp" />
<div align="center">
    <form method="post" action="adminLogin">
        Login:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="login" /><br/>
        <br/>
        Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="password" /><br/>
        <br/>
        <input type="submit" value="Login" />
    </form>
</div>
</body>
</html>