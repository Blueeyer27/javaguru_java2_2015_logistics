<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Error</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="center">
    Oops! Something went wrong:<br/>
    <% String errorMessage = (String)request.getAttribute("model"); %>
    <%if(errorMessage != null) {%>
        <font color="red">Errors: <br/><%=errorMessage%></font><br>
    <%}%>
    Please go back and correct data.<br/>
</div>
</body>
</html>

