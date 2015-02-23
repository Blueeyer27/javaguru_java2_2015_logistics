<%@ page import="lv.javaguru.java2.domain.Agreement" %>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Agreement processed!</title>
    <meta http-equiv="REFRESH" content="0;url=agreementOverview"></HEAD>

</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="agreementOverview">BACK</a></div>
<hr />
<div align="center">
    <%String processType = (String) request.getAttribute("model");%>
    <b>Agreement! processed.<BR>
        processType= <%=processType%><BR><BR>
        Yes= updates status=ACCEPTED.<BR>
        No = delete whole agreement item.<BR>
        -=Press BACK to see updated overview!</b><br>
</div>

<%--
<script language="Javascript">
    <!--
    alert ("Congratulations! :) Agreement processed processType= <%=processType%>")
    //-->
</script>
--%>

</body>
</html>

