package TaskManagerMain.TaskManager.server.modelServer;

import TaskManagerMain.TaskManager.common.entity.*;
import TaskManagerMain.TaskManager.common.service.GenericDao;
import TaskManagerMain.TaskManager.common.service.JDBCDao;
import TaskManagerMain.TaskManager.common.service.JDBCDaoTask;
import org.junit.Before;
import org.mockito.*;
import org.mockito.internal.matchers.Equals;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskManagerModelImplAddTaskTest {

    Task task = new TaskImpl("1", "2", "3", "4", "4");
    JDBCDaoTask dao = mock(JDBCDaoTask.class);
    TaskManagerModel model = new TaskManagerModelImpl(dao, dao);

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