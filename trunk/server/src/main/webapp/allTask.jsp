<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <td>
            TASK NAME
        </td>
        <td>
            DESCRIPTION
        </td>
        <td>
            DEADLINE
        </td>
        <td>
            PRIORITY
        </td>
        <td>
            STATUS
        </td>
    </tr>

    <%  GenericDao daoTask = new JDBCDaoTask();
        List<Task> allTask = daoTask.readAll();
        for (Task task: allTask){

    %>
    <tr>
        <td>
            <%=task.getTaskName() %>
        </td>
        <td>
            <%=task.getDescription() %>
        </td>
        <td>
            <%=task.getDeadline() %>
        </td>
        <td>
            <%=task.getPriority() %>
        </td>
        <td>
            <%=task.getStatus() %>
        </td>
    </tr>

    <%
        }
    %>
</table>

<a href="main.jsp">Main</a>

</body>
</html>