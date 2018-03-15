package labs.taskmanger.server.modelServer;

import labs.taskmanger.common.entity.*;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.common.service.JDBCDaoTask;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskManagerModelImplTest {

    Assignee assignee;
    Task task;
    GenericDao<Assignee> daoAssignee;
    GenericDao<Task> daoTask;
    TaskManagerModel model;
    String regexp;
    String name;


    @Before
    public void inicialize(){
        regexp = "1";
        name = "1";
        assignee = new AssigneeImpl("1", "2", "3");
        task = new TaskImpl("1", "2", "3", "4", "4");
        daoAssignee = mock(GenericDao.class);
        daoTask = mock(GenericDao.class);
        model = new TaskManagerModelImpl(daoTask, daoAssignee);
    }

    @Test
    public void testAddAssignee() {

        model.addAssaignee(assignee);


        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(name);

        verify(daoAssignee).create(assignee);
        assertEquals(true, matcher.matches());
        verify(daoAssignee, atLeastOnce()).create(assignee);

    }

    @Test
    public void testAddAllAssignee() {

        model.addAllAssignee();

        verify(daoAssignee, atLeastOnce()).readAll();

    }

    @Test
    public void testDeleteAssignee() {

        model.deleteAssignee(assignee);

        verify(daoAssignee).delete(assignee);
        verify(daoAssignee, atLeastOnce()).delete(assignee);

    }

    @Test
    public void testUpdateAssignee() {

        model.updateAssignee(assignee);

        verify(daoAssignee).update(assignee);
        verify(daoAssignee, atLeastOnce()).update( assignee);

    }

    @Test
    public void testAddTask() {

        model.addTask(task);

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(name);

        verify(daoTask).create(task);
        assertEquals(true, matcher.matches());
        verify(daoTask, atLeastOnce()).create(task);

    }

    @Test
    public void testAddAllTask() {

        model.addAllTask();

        verify(daoTask, atLeastOnce()).readAll();

    }

    @Test
    public void testDeleteTask() {

        model.deleteTask(task);

        verify(daoTask).delete(task);
        verify(daoTask, atLeastOnce()).delete(task);

    }

    @Test
    public void testUpdateTask() {

        model.updateTask(task);

        verify(daoTask).update(task);
        verify(daoTask, atLeastOnce()).update(task);

    }

}