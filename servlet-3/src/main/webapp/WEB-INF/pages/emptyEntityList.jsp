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
<% Object beanList = request.getAttribute("passportBeans");%>
<% if (beanList != null) { %>
    <table>
        <% for (PassportBean3 passport : (List<PassportBean3>) request.getAttribute("passportBeans")) { %>
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