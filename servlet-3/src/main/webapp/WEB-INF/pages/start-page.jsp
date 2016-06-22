<%@ page import="java.util.List" %>
<%@ page import="getman.ejb3.entity.passport.PassportBean3" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>TaskEE</title>
</head>
<body>
entity objects
<table>
    <% for (PassportBean3 task : (List<PassportBean3>) request.getAttribute("passportBeans")) { %>
        <tr>
            <td>id: <%= task.getPassid() %></td>
            <td>number: <%= task.getNumber() %></td>
            <td>country: <%= task.getCountry() %></td>
        </tr>
    <% } %>
</table>
entity object poperties
<table>
    <tr>
        <td>id: <%= request.getAttribute("passId") %></td>
        <td>number: <%= request.getAttribute("passNumber") %></td>
    </tr>
</table>

</body>
</html>