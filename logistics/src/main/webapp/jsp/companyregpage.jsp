<%@ page import="lv.javaguru.java2.domain.Value" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.User" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="style.css">
  <title>Add new company:</title>
</head>
<jsp:include page="header.jsp" />
<div align="center">
  <form method="post" action="companyReg">
    Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="name" /><br/>
    <br/>
    Registration number:<input type="text" name="regNumber" /><br/>
    <br/>
    Registration address:<input type="text" name="regAddress" /><br/>
    <br/>
    Actual address:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="actualAddress" /><br/>
    <br/>
    Bank:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="bank" /><br/>
    <br/>
    IBAN:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="iban" /><br/>
    <br/>
    Country:
    <select name="country">
        <%List<Value> countries = (List<Value>) request.getAttribute("model");%>
        <%for (Value country : countries) {%>
          <option value=<%=country.getValue()%> >&nbsp;<%=country.getValue()%>
        <%}%>
    </select><br/>
    <br/>
    Type:
    <select name="type">
      <option value="transport" selected>&nbsp;transport
      <option value="cargo" >&nbsp;cargo
    </select><br/>
    <br/>
    <input type="submit" value="register" />
  </form>
</div>
</body>
</html>
