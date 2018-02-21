package labs.taskmanger.server.modelServer;

import labs.taskmanger.common.entity.*;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.common.service.JDBCDaoTask;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskManagerModelImplTest {

    Assignee assignee;
    Task task;
    GenericDao<Assignee> daoAssignee;
    GenericDao<Task> daoTask;
    TaskManagerModel model;


    @Before
    public void inicialize(){
        assignee = new AssigneeImpl("1", "2", "3");
        task = new TaskImpl("1", "2", "3", "4", "4");
        daoAssignee = mock(GenericDao.class);
        daoTask = mock(GenericDao.class);
        model = new TaskManagerModelImpl(daoTask, daoAssignee);
    }

    @Captor
    ArgumentCaptor<Assignee> assigneeArgumentCaptor = ArgumentCaptor.forClass(Assignee.class);

    @Captor
    ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);



    @Test
    public void testAddAssignee() {

        model.addAssaignee(assignee);

        verify(daoAssignee).create(assigneeArgumentCaptor.capture());
        assertEquals("1", assigneeArgumentCaptor.getValue().getName());
        verify(daoAssignee, atLeastOnce()).create(assignee);

    }

    @Test
    public void testDeleteAssignee() {

        model.deleteAssignee(assignee);

        verify(daoAssignee).delete(assigneeArgumentCaptor.capture());
        verify(daoAssignee, atLeastOnce()).delete(assignee);

    }

    @Test
    public void testUpdateAssignee() {

        model.updateAssignee(assignee);

        verify(daoAssignee).update(assigneeArgumentCaptor.capture());
        verify(daoAssignee, atLeastOnce()).update( assignee);

    }

    @Test
    public void testAddTask() {

        model.addTask(task);

        verify(daoTask).create(taskArgumentCaptor.capture());
        assertEquals("1", taskArgumentCaptor.getValue().getTaskName());
        verify(daoTask, atLeastOnce()).create(task);

    }

    @Test
    public void testDeleteTask() {

        model.deleteTask(task);

        verify(daoTask).delete(taskArgumentCaptor.capture());
        verify(daoTask, atLeastOnce()).delete(task);

    }

    @Test
    public void testUpdateTask() {

        model.updateTask(task);

        verify(daoTask).update(taskArgumentCaptor.capture());
        verify(daoTask, atLeastOnce()).update(task);

    }

}