<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Assignees</title>

    </head>

    <body>
    <form action="/AssigneeServlet" method="post">
        <table>
            <tr>
                <td>Assignee Name</td>
                <td><input type="text" name="asigneeName" value="${assignee.assigneeName}"></td>
            </tr>
            <tr>
                <td>Assignee Last Name</td>
                <td><input type="text" name="asigneeLastName" value="${assignee.assigneeLastName}"></td>
            </tr>
            <tr>
                <td>Assignee Name</td>
                <td><input type="text" name="asigneePost" value="${assignee.assigneePost}"></td>
            </tr>

            <tr>
                <td><input type="submit" name="action" value="Add"></td>
                <td><input type="submit" name="action" value="Edit"></td>
                <td><input type="submit" name="action" value="Delete"></td>
                <td><input type="submit" name="action" value="Search"></td>
            </tr>
        </table>
    </form>
    <br>
    <table border="1">
        <th>NAME</th>
        <th>LAST NAME</th>
        <th>POST</th>

        <c:forEach items="allAssignee" var="assignee">
            <td>${assignee.assigneeName}</td>
            <td>${assignee.assigneeLastName}</td>
            <td>${assignee.assigneePost}</td>
        </c:forEach>
    </table>
    </body>
</html>