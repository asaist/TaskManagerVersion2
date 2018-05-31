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

    </c:forEach>

            <table>
                <form name="allTaskForm" action="loadTask" method="post">
                    <td><input type="Submit" name="action" value = "Load All Task"></td>
                </form>
            </table>

            <table>
                <form name="exportForm" action="loadTask" method="post">
                    <td><input type="Submit" name="action" value = "Export"></td>
                </form>
            </table>

    <table>

    <tr>
        <th>
            TASK NAME
        </th>

        <th>
            STATUS
        </th>

    </tr>

    <tr>
        <form name="searchForm" action="loadTask" method="post">
            <td> <input type="text" name="nameTask" value="<c:out value = "${taskNameSearch}" />" /></td>
            <td> <input type="text" name="statusTask" value="<c:out value = "${statusSearch}" />" /></td>
            <td><input type="Submit" name="action" value="Search"></td>
        </form>
    </tr>
    </table>

    <table>
        <tr>
            <td></td>
        </tr>
    </table>


    <table>

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
    <tr>
        <form name="addForm" action="loadTask" method="post">
            <td> <input type="text" name="nameTask" value="<c:out value = "${taskNameAdd}" />" /></td>
            <td> <input type="text" name="descriptionTask" value="<c:out value = "${descriptionAdd}" />" /></td>
            <td> <input type="text" name="deadlineTask" value="<c:out value = "${deadlineAdd}" />" /></td>
            <td> <input type="text" name="priorityTask" value="<c:out value = "${priorityAdd}" />" /></td>
            <td> <input type="text" name="statusTask" value="<c:out value = "${statusAdd}" />" /></td>
            <td><input type="Submit" name="action" value="Add"></td>
        </form>
    </tr>
</table>

    <table>
        <tr>
            <td></td>
        </tr>
    </table>


    <table>

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
    <tr>
        <form name="deleteForm" action="loadTask" method="post">
            <td> <input type="text" name="nameTask" value="<c:out value = "${taskNameDelete}" />" /></td>
            <td> <input type="text" name="descriptionTask" value="<c:out value = "${descriptionDelete}" />" /></td>
            <td> <input type="text" name="deadlineTask" value="<c:out value = "${deadlineDelete}" />" /></td>
            <td> <input type="text" name="priorityTask" value="<c:out value = "${priorityDelete}" />" /></td>
            <td> <input type="text" name="statusTask" value="<c:out value = "${statusDelete}" />" /></td>
            <td><input type="Submit" name="action" value="Delete"></td>
        </form>
    </tr>
    </table>

</table>

<table>
    <tr>
        <td></td>
    </tr>
</table>

<a href="main.jsp">Main</a>

</body>
</html>