<%@ page import="lv.javaguru.java2.domain.Agreement" %>
<!DOCTYPE html>
<%Agreement agreement = (Agreement) request.getAttribute("model");%>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Agreement Nr.<%=agreement.getAgreementId()%></title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="../java2">Back to home</a></div>
<hr />
<div align="center">
<HR width=220>
    <b>Created new Agreement!</b><br>
    <b>Agreement <U>Id</U> = <%=agreement.getAgreementId()%></b><br>
<HR width=220>
    <b>Cargo <U>Id</U> = <%=agreement.getCargo().getCargoId()%></b><br>
    <b>Cargo <U>User Id</U> = <%=agreement.getCargo().getUser().getUserId()%></b><br>
    <b>Cargo <U>Company Id</U> = <%=agreement.getCargo().getUser().getCompany().getCompanyId()%></b><br>
<HR width=220>
    <b>Vehicle <U>Id</U> = <%=agreement.getVehicle().getVehicleId()%></b><br>
    <b>Vehicle <U>User Id</U> = <%=agreement.getVehicle().getUser().getUserId()%></b><br>
    <b>Vehicle <U>Company</U> Id = <%=agreement.getVehicle().getUser().getCompany().getCompanyId()%></b><br>
<HR width=220>
    <b>Status = <U><%=agreement.getStatus()%></U></b><br>
<HR width=220>

</div>

<script language="Javascript">
    <!--
    alert ("Congratulations! :) Agreement created with ID =<%=agreement.getAgreementId()%>")
    //-->
</script>

</body>
</html>

