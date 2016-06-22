<%@ page import="java.util.List" %>
<%@ page import="getman.ejb3.entity.cmp.passpot.PassportBean3" %>
<%@ page import="getman.ejb3.entity.cmp.passpot.HumanEntity3" %>
<%@ page import="getman.ejb3.entity.cmp.passpot.DriverId" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>TaskEE</title>
</head>
<body>
passport objects
<table>
    <% for (PassportBean3 passport : (List<PassportBean3>) request.getAttribute("passportBeans")) { %>
        <tr>
            <td>id: <%= passport.getPassid() %></td>
            <td>number: <%= passport.getNumber() %></td>
            <td>country: <%= passport.getCountry() %></td>
        </tr>
    <% } %>
</table>
human objects
<table>
    <% for (HumanEntity3 human : (List<HumanEntity3>) request.getAttribute("humanEntities")) { %>
        <tr>
            <td>id: <%= human.getHumanid() %></td>
            <td>name: <%= human.getName() %></td>
            <td>surname: <%= human.getSurname() %></td>
        </tr>
    <% } %>
</table>
driverid objects
<table>
    <% for (DriverId driverid : (List<DriverId>) request.getAttribute("driveridEntities")) { %>
        <tr>
            <td>id: <%= driverid.getDriverid() %></td>
            <td>number: <%= driverid.getNumber() %></td>
        </tr>
    <% } %>
</table>
</body>
</html>