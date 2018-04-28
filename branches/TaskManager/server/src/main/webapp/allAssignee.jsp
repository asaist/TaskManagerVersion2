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
    <title>All Assignee</title>
</head>
<body>

<table border="1">
    <tr>
        <th>
            NAME
        </th>

        <th>
            LAST NAME
        </th>
        <th>
            POST
        </th>

    </tr>
    <c:forEach items="${assignees}" var="assignee">

        <tr>
            <td> <input type="text" name="nameAssignee" value="<c:out value = "${assignee.name}" />"></td>
            <td> <input type="text" name="lastNameAssignee" value="<c:out value = "${assignee.lastName}" />"></td>
            <td> <input type="text" name="postAssignee" value="<c:out value = "${assignee.post}" />"></td>

            <form name="exportForm" action="http://localhost:8080/exportAssignee" method="post">
                <td><input type="text" name="nameExport" value = "<c:out value = "${assignee.name}" />"></td>
                <td><input type="text" name="lastNameExport" value = "<c:out value = "${assignee.lastName}" />"></td>
                <td><input type="text" name="postExport" value = "<c:out value = "${assignee.post}" />"></td>
                <td><input type="Submit" name="Submit" value = "Export"></td>
            </form>
        </tr>

    </c:forEach>

    <tr>
        <form name="searchForm" action="http://localhost:8080/loadAssignee" method="post">
            <td><input type="text" name="name" value="Name"></td>
            <td><input type="text" name="lastName" value="Last Name"></td>
            <td><input type="Submit" name="Submit" value="Search"></td>
        </form>
    </tr>

</table>

<a href="main.jsp">Main</a>



</body>
</html>