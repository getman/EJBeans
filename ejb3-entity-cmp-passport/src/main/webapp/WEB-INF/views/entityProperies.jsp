<%@ page import="java.util.List" %>
<%@ page import="getman.ejb3.entity.cmp.passpot.PassportBean3" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>TaskEE</title>
</head>
<body>
entity object poperties
<table>
    <tr>
        <td>id: <%= request.getAttribute("passId") %></td>
        <td>number: <%= request.getAttribute("passNumber") %></td>
    </tr>
</table>

</body>
</html>