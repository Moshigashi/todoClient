<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 02/06/2023
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="../css/style.css">
<html>
<head>
    <title>Todo Management</title>
</head>
<body>
<form action="LoginServlet" method="POST">
    <h1>Login Form</h1>
    <table>
        <tbody>
        <tr>
            <td><label>Username: </label> </td>
            <td><input type="text" name = "username" value="${prenom}" /></td>
        </tr>
        <tr>
            <td><label>Password: </label> </td>
            <td><input type="password" name = "password"/></td>
        </tr>
        <tr>
            <td><label></label> </td>
            <td><input type="submit" value = "Login"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
