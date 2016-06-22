<%@ page import="java.util.List" %>
<%@ page import="getman.ejb3.entity.passport.PassportBean3" %>
<%@ page import="getman.ejb3.entity.passport.HumanEntity3" %>
<%@ page import="getman.ejb3.entity.passport.DriverId" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>TaskEE</title>
</head>
<body>
passport objects
<% Object passList = request.getAttribute("passportBeans");%>
<% if (passList != null) { %>
    <table>
        <% for (PassportBean3 passport : (List<PassportBean3>) passList) { %>
            <tr>
                <td>id: <%= passport.getPassid() %></td>
                <td>number: <%= passport.getNumber() %></td>
                <td>country: <%= passport.getCountry() %></td>
            </tr>
        <% } %>
    </table>
<% } else { %>
      no passport
<% } %>
<p></p>

human objects
<% Object humanList = request.getAttribute("humanEntities");%>
<% if (humanList != null) { %>
    <table>
        <% for (HumanEntity3 human : (List<HumanEntity3>) humanList) { %>
            <tr>
                <td>id: <%= human.getHumanid() %></td>
                <td>name: <%= human.getName() %></td>
                <td>surname: <%= human.getSurname() %></td>
            </tr>
        <% } %>
    </table>
<% } else { %>
      no humans
<% } %>
<p></p>

driverid objects
<% Object driverList = request.getAttribute("driveridEntities");%>
<% if (driverList != null) { %>
    <table>
        <% for (DriverId driverid : (List<DriverId>) driverList) { %>
            <tr>
                <td>id: <%= driverid.getDriverid() %></td>
                <td>number: <%= driverid.getNumber() %></td>
            </tr>
        <% } %>
    </table>
<% } else { %>
      no driver ids
<% } %>
<form method="POST" action="persons">
    <p>
        <label for="item">New passport:</label>
        <input id="passport" type="text" name="passport"/>
        <input type="hidden" name="action" value="add"/>
        <input type="submit" value="add"/>
    </p>

</form>
<form method="POST" action="persons">
    <p>
        <label for="item">New passport:</label>
        <input id="idToRemove" type="text" name="idToRemove"/>
        <input type="hidden" name="action" value="remove"/>
        <input type="submit" value="remove"/>
    </p>
</form>
</body>
</html>