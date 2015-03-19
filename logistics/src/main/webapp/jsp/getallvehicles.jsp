<%@ page import="lv.javaguru.java2.domain.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 19.02.2015
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="style.css">
  <title>Vehicle list</title>
</head>
<body>
<div align="center"><img src="images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="../java2">Back to home</a></div>
<hr />
<div align="center">
  <table align="center" border="1" width="450">
    <tr>
    <td width="200"><b>Vehicle Name</b></td>
    <td width="200"><b>Vehicle Type</b></td>
    <td width="200"><b>Capacity</b></td>
    <td width="200"><b>Status</b></td>
    <td width="200"><b>Additional information</b></td>
    </tr>




<%
  List<Vehicle> vehicleList = (ArrayList<Vehicle>)request.getAttribute("model");
  Vehicle vehicle = null;

  for(int i=0;i<vehicleList.size();i++){
    vehicle = vehicleList.get(i);
     String vehicleId = "showDetailsVehicle?id="+vehicle.getVehicleId();
  %>




      <tr>
        <td width="200"><%=vehicle.getName()%></td>
        <td width="200"><%=vehicle.getType()%></td>
        <td width="200"><%=vehicle.getCapacity()%></td>
        <td width="200"><%=vehicle.getStatus()%></td>
        <td><a href="<%=vehicleId%>">Information</a>
       </td>

<%}%>

        <select name="country">
      <%
            CountryDAOImpl countryDAO = new CountryDAOImpl();
            List<Country> countries = countryDAO.getAll();
            if (countries.size()>0)
                for (Country country : countries) {
                %>
        <option value=<%=country.getCountryId()%>> <%=country.getName()%>
            <%  }
            else {%>
        <option value="1">! id=1 no countries
            <%}%>
        </select>

</body>
</html>
