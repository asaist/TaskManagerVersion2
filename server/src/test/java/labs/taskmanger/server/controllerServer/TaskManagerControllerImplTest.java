package labs.taskmanger.server.controllerServer;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;
import labs.taskmanger.common.entity.Task;
import labs.taskmanger.common.entity.TaskImpl;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.server.modelServer.TaskManagerModel;
import labs.taskmanger.server.modelServer.TaskManagerModelImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskManagerControllerImplTest {

    Assignee assignee;
    Task task;
    GenericDao<Assignee> daoAssignee;
    GenericDao<Task> daoTask;
    TaskManagerModel model;
    TaskManagerController controller;
    String regexp;
    String name;
    String lastName;
    String post;
    String taskName;
    String description;
    String deadline;
    String priority;
    String status;


    @Before
    public void inicialize(){
        regexp = "1";
        name = "1";
        lastName = "2";
        post = "3";
        taskName = "1";
        description = "2";
        deadline = "3";
        priority = "4";
        status = "5";
        assignee = new AssigneeImpl("1", "2", "3");
        task = new TaskImpl("1", "2", "3", "4", "5");
        model = mock(TaskManagerModelImpl.class);
        controller = new TaskManagerControllerImpl(model);
    }

    @Test
    public void testAddTask() {

        controller.addTask(taskName, description, deadline, priority, status);

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(taskName);

        verify(model).addTask(task);
        assertEquals(true, matcher.matches());
        verify(model, atLeastOnce()).addTask(task);

    }

    @Test
    public void testDeleteTask() {

        controller.deleteTask(task);

        verify(model).deleteTask(task);
        verify(model, atLeastOnce()).deleteTask(task);

    }

    @Test
    public void testUpdateTask() {

        controller.updateTask(task);

        verify(model).updateTask(task);
        verify(model, atLeastOnce()).updateTask(task);

    }

    @Test
    public void testAddAssignee() {

        controller.addAssignee(name, lastName, post);

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(name);

        verify(model).addAssaignee(assignee);
        assertEquals(true, matcher.matches());
        verify(model, atLeastOnce()).addAssaignee(assignee);

    }

    @Test
    public void testDeleteAssignee() {

        controller.deleteAssignee(assignee);

        verify(model).deleteAssignee(assignee);
        verify(model, atLeastOnce()).deleteAssignee(assignee);

    }

    @Test
    public void testUpdateAssignee() {

        controller.updateAssignee(assignee);

        verify(model).updateAssignee(assignee);
        verify(model, atLeastOnce()).updateAssignee(assignee);

    }

}