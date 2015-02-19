<%@ page import="lv.javaguru.java2.domain.Agreement" %>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Search result</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="index.html">BACK</a></div>
<hr />
<div align="center">
    <%Agreement agreement = (Agreement) request.getAttribute("model");%>
    <b>Created new Agreement!</b><br>
    <b>Agreement id = <%=agreement.getAgreementId()%></b><br>
    <b>Cargo Id = <%=agreement.getCargoId()%></b><br>
    <b>Vehicle id = <%=agreement.getVehicleId()%></b><br>
    <b>Status = <%=agreement.getStatus()%></b><br>
</div>
</body>
</html>

