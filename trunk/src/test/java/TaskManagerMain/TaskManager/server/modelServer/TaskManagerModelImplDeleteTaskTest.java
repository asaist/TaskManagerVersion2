package TaskManagerMain.TaskManager.server.modelServer;

import TaskManagerMain.TaskManager.common.entity.Entity;
import TaskManagerMain.TaskManager.common.entity.Task;
import TaskManagerMain.TaskManager.common.entity.TaskImpl;
import TaskManagerMain.TaskManager.common.service.JDBCDaoTask;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskManagerModelImplDeleteTaskTest {

    Task task = new TaskImpl("1", "2", "3", "4", "4");
    JDBCDaoTask dao = mock(JDBCDaoTask.class);
    TaskManagerModel model = new TaskManagerModelImpl(dao, dao);

    @Captor
    ArgumentCaptor<TaskImpl> entityArgumentCaptor = ArgumentCaptor.forClass(TaskImpl.class);;

    @Test
    public void testDeleteTask() {

        model.deleteTask(task);

        verify(dao).delete(entityArgumentCaptor.capture());
        verify(dao, atLeastOnce()).delete((Entity) task);

    }
}