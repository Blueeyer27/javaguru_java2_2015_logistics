<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Error</title>
</head>
<body>
<%if (session.getAttribute("pageName").equals("UserLogin")) { %>
<div align="center"><img src="../images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="../index.html">BACK</a></div>
<hr />
<%} else {%>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<%}%>

<div align="center">
    Oops! Something went wrong:<br/>
    <% String errorMessage = (String)request.getAttribute("model"); %>
    <%if(errorMessage != null) {%>
        <font color="red">Errors: <br/><%=errorMessage%></font><br>
    <%}%>
    Please go back and correct data.<br/><br/>source page:<%=session.getAttribute("pageName")%>
</div>
</body>
</html>

