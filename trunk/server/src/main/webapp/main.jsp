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
    <title>TaskManager</title>
</head>
<body>

<a href="http://localhost:8080/loadTask">All Task</a>
<a href="http://localhost:8080/loadAssignee">All Assignee</a>
</body>
</html>