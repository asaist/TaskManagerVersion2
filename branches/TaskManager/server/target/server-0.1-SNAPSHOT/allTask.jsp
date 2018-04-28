<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



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

    <c:forEach items="${tasks}" var="task">

        <tr>
            <td> <c:out value = "${task.taskName}" /></td>
            <td> <c:out value = "${task.description}"/></td>
            <td> <c:out value = "${task.deadline}"/></td>
            <td> <c:out value = "${task.priority}"/></td>
            <td> <c:out value = "${task.status}"/></td>
        </tr>

    </c:forEach>


    <tr>
        <form name="searchForm" action="http://localhost:8080/loadTask" method="post">
            <td><input type="text" name="nameTask" value="TaskName"></td>
            <td><input type="text" name="post" value="Status"></td>
            <td><input type="Submit" name="Submit" value="Search"></td>
        </form>
    </tr>



</table>

<a href="main.jsp">Main</a>

</body>
</html>