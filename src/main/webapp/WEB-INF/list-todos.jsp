<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 02/06/2023
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Todo Management</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<!-- ${TODO_LIST}-->
<div id="wrapper">
    <div id="header">
        <h2>List of task to do</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <c:if test="${!empty sessionScope.username}">
            <h1>Bienvenue ${sessionScope.username}</h1>
            <form action="LogoutServlet" method="get">
                <input type="submit" value="logout"/>
            </form>
        </c:if>
        <table>
            <tr>
                <th>task Id </th>
                <th>Description</th>
                <th>Assignment </th>
                <th>Status </th>
                <th>Action </th>

            </tr>
            <c:forEach var="tempTodo" items="${TODO_LIST }" >
            <c:url var="EditLink" value= "AchieveTodoServlet">
                <c:param name="todoId" value="${tempTodo.todoId}"/>
            </c:url>
            <tr>
                <td> ${tempTodo.todoId}</td>
                <td> ${tempTodo.todoDescription}</td>
                <td> ${tempTodo.todoAssignment}</td>
                <td> ${tempTodo.todoStatus}</td>
                <td> <a href="${EditLink }"> Done</a></td>
                </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
