<%@ page import="labs.taskmanger.common.service.JDBCDaoAssignee" %>
<%@ page import="labs.taskmanger.common.service.GenericDao" %>
<%@ page import="labs.taskmanger.common.entity.Entity" %>
<%@ page import="java.util.List" %>
<%@ page import="labs.taskmanger.common.entity.Assignee" %>
<%@ page import="labs.taskmanger.common.service.JDBCDaoTask" %>
<%@ page import="labs.taskmanger.common.entity.Task" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TaskManager</title>
</head>
<body>
<table border="1">
    <tr>
        <td>
            NAME
        </td>
        <td>
            LAST NAME
        </td>
        <td>
            POST
        </td>
    </tr>
    <%  GenericDao daoAssignee = new JDBCDaoAssignee();
        List<Assignee> allAssignee = daoAssignee.readAll();
        for (Assignee assignee: allAssignee){

    %>
    <tr>
        <td>
            <%assignee.getName(); %>
        </td>
        <td>
            <%assignee.getLastName(); %>
        </td>
        <td>
            <%assignee.getPost(); %>
        </td>
    </tr>

    <%
        }
    %>
</table>

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
            <%task.getTaskName(); %>
        </td>
        <td>
            <%task.getDescription(); %>
        </td>
        <td>
            <%task.getDeadline(); %>
        </td>
        <td>
            <%task.getPriority(); %>
        </td>
        <td>
            <%task.getStatus(); %>
        </td>
    </tr>

    <%
        }
    %>
</table>

</body>
</html>