<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="labs.taskmanger.common.service.JDBCDaoAssignee" %>
<%@ page import="labs.taskmanger.common.service.GenericDao" %>
<%@ page import="labs.taskmanger.common.entity.Entity" %>
<%@ page import="java.util.List" %>
<%@ page import="labs.taskmanger.common.entity.Assignee" %>
<%@ page import="labs.taskmanger.common.service.JDBCDaoTask" %>
<%@ page import="labs.taskmanger.common.entity.Task" %>
<%@ page import="labs.taskmanger.common.entity.AssigneeImpl" %>

<html>
<head>
    <title>All Task</title>
</head>
<body>

<table border="1">
    <tr>
        <th>
            TASK NAME
        </th>

        <th>
            DESCRIPTION
        </th>

        <th>
            DEADLINE
        </th>

        <th>
            PRIORITY
        </th>

        <th>
            STATUS
        </th>

    </tr>

    <c:forEach items="${tasks}" var="task">

        <tr>
            <td> <input type="text" name="nameTask" value="<c:out value = "${task.taskName}" />"></td>
            <td> <input type="text" name="descriptionTask" value="<c:out value = "${task.description}" />"></td>
            <td> <input type="text" name="deadlineTask" value="<c:out value = "${task.deadline}" />"></td>
            <td> <input type="text" name="priorityTask" value="<c:out value = "${task.priority}" />"></td>
            <td> <input type="text" name="statusTask" value="<c:out value = "${task.status}" />"></td>

            <form name="exportForm" action="exportTask" method="post">
                <td> <input type="text" name="nameTask" value="<c:out value = "${task.taskName}" />"></td>
                <td> <input type="text" name="descriptionTask" value="<c:out value = "${task.description}" />"></td>
                <td> <input type="text" name="deadlineTask" value="<c:out value = "${task.deadline}" />"></td>
                <td> <input type="text" name="priorityTask" value="<c:out value = "${task.priority}" />"></td>
                <td> <input type="text" name="statusTask" value="<c:out value = "${task.status}" />"></td>
            </form>
        </tr>

    </c:forEach>

    <table>
        <tr>
            <td></td>
        </tr>
    </table>

    <tr>
        <form name="searchForm" action="loadTask" method="post">
            <td> <input type="text" name="nameTask" value="TaskName" /></td>
            <td> <input type="text" name="descriptionTask" value="Description" /></td>
            <td> <input type="text" name="deadlineTask" value="2016-11-09 10:30" /></td>
            <td> <input type="text" name="priorityTask" value="Priority" /></td>
            <td> <input type="text" name="statusTask" value="Status" /></td>
            <td><input type="Submit" name="action" value="Search"></td>
        </form>
    </tr>

    <table>
        <tr>
            <td></td>
        </tr>
    </table>

    <tr>
        <form name="addForm" action="loadTask" method="post">
            <td> <input type="text" name="nameTask" value="TaskName" /></td>
            <td> <input type="text" name="descriptionTask" value="Description" /></td>
            <td> <input type="text" name="deadlineTask" value="2016-11-09 10:30" /></td>
            <td> <input type="text" name="priorityTask" value="Priority" /></td>
            <td> <input type="text" name="statusTask" value="Status" /></td>
            <td><input type="Submit" name="action" value="Add"></td>
        </form>
    </tr>

    <table>
        <tr>
            <td></td>
        </tr>
    </table>

    <tr>
        <form name="deleteForm" action="loadTask" method="post">
            <td> <input type="text" name="nameTask" value="TaskName" /></td>
            <td> <input type="text" name="descriptionTask" value="Description" /></td>
            <td> <input type="text" name="deadlineTask" value="2016-11-09 10:30" /></td>
            <td> <input type="text" name="priorityTask" value="Priority" /></td>
            <td> <input type="text" name="statusTask" value="Status" /></td>
            <td><input type="Submit" name="action" value="Delete"></td>
        </form>
    </tr>

</table>

<table>
    <tr>
        <td></td>
    </tr>
</table>

<a href="main.jsp">Main</a>

</body>
</html>