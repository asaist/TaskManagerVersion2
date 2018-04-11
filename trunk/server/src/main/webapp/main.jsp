<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="labs.taskmanger.common.service.JDBCDaoAssignee" %>
<%@ page import="labs.taskmanger.common.service.GenericDao" %>
<%@ page import="labs.taskmanger.common.entity.Entity" %>
<%@ page import="java.util.List" %>
<%@ page import="labs.taskmanger.common.entity.Assignee" %>
<%@ page import="labs.taskmanger.common.service.JDBCDaoTask" %>
<%@ page import="labs.taskmanger.common.entity.Task" %>
<%@ page import="labs.taskmanger.common.entity.AssigneeImpl" %>

<!DOCTYPE html>
<html>
<head>
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
        List<Entity> allAssignee = daoAssignee.readAll();
        for (Entity assignee: allAssignee){
            AssigneeImpl assignee1 = (AssigneeImpl) assignee;
    %>

    <tr>
        <td> <%= assignee1.getName() %> </td>
        <td> <%= assignee1.getLastName() %> </td>
        <td> <%= assignee1.getPost() %> </td>
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

</body>
</html>