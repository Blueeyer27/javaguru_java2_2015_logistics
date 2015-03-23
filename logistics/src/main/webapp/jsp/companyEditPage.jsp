<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.Company" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="style.css">
  <title>Edit company info:</title>
</head>
<jsp:include page="adminHeader.jsp" />
<div align="center">
<%
    Map<String, Object> modelMap = (Map<String, Object>) request.getAttribute("model");
    Company company = (Company) modelMap.get("company");
    List<String> countries = (List<String>) modelMap.get("countries");
    List<String> companyTypes = (List<String>) modelMap.get("companyTypes");
%>
  <form method="post" action="companyUpdate">
    <input type="hidden" name="companyId" value="<%=company.getCompanyId()%>">
    Name:                   <input type="text" name="name" value="<%=company.getName()%>" /><br/><br/>
    Registration number:    <input type="text" name="regNumber"  value="<%=company.getRegNumber()%>"/><br/><br/>
    Registration address:   <input type="text" name="regAddress"  value="<%=company.getRegAddress()%>"/><br/><br/>
    Actual address:         <input type="text" name="actualAddress"  value="<%=company.getActualAddress()%>"/><br/><br/>
    Bank:                   <input type="text" name="bank"  value="<%=company.getBank()%>"/><br/><br/>
    IBAN:                   <input type="text" name="iban"  value="<%=company.getIban()%>"/><br/><br/>
    Country:
    <select name="country">
      <option selected="true" style="display:none;"><%=company.getCountry()%></option>
      <%for (String country : countries) {%>
      <option><%=country%></option>
      <%}%>
    </select><br/><br/>
    Type:
    <select name="type">
      <option selected="true" style="display:none;"><%=company.getType()%></option>
      <%for (String type : companyTypes) {%>
      <option><%=type%></option>
      <%}%>
    </select><br/><br/>
    <input type="submit" value="Update">
    <input type="reset" value="Reset changes">
  </form>
    <form action="manageCompanies">
        <input type="submit" value="Cancel">
    </form>
</div>
</body>
</html>
