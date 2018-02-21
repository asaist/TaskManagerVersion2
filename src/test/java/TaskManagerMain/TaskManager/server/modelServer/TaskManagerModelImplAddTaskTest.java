package TaskManagerMain.TaskManager.server.modelServer;

import TaskManagerMain.TaskManager.common.entity.*;
import TaskManagerMain.TaskManager.common.service.JDBCDaoAssignee;
import TaskManagerMain.TaskManager.common.service.JDBCDaoTask;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskManagerModelImplAddTaskTest {

    Task task;
    JDBCDaoTask dao;
    TaskManagerModel model;

    @Before
    public void inicialize(){
        task = new TaskImpl("1", "2", "3", "4", "4");
        dao = mock(JDBCDaoTask.class);
        model = new TaskManagerModelImpl(dao, dao);

    }

    @Captor
    ArgumentCaptor<TaskImpl> entityArgumentCaptor = ArgumentCaptor.forClass(TaskImpl.class);;

    @Test
    public void testAddTask() {

        model.addTask(task);

        verify(dao).create(entityArgumentCaptor.capture());
        assertEquals("1", entityArgumentCaptor.getValue().getTaskName());
        verify(dao, atLeastOnce()).create((Entity) task);

    }

}