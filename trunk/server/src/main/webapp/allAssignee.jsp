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
            <td> <c:out value = "${assignee.name}" /></td>
            <td> <c:out value = "${assignee.lastName}"/></td>
            <td> <c:out value = "${assignee.post}"/></td>
        </tr>

    </c:forEach>

    <%  GenericDao daoAssignee = new JDBCDaoAssignee();
        List<Entity> allAssignee = daoAssignee.readAll();
        for (Entity assignee: allAssignee){
            AssigneeImpl assignee1 = (AssigneeImpl) assignee;
    %>

    <%
        }
    %>


    <tr>
        <td>
            <button type="button" onclick="">Search</button>
        </td>

        <td>
            <input type="text" id="name" name="name" value="Name"/>
        </td>

        <td>
            <input type="text" id="lastName" name="lastName" value="Last Name" />
        </td>
    </tr>
</table>

<a href="main.jsp">Main</a>



</body>
</html>