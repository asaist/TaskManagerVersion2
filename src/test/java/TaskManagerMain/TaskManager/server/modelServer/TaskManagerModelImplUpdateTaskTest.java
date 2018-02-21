package TaskManagerMain.TaskManager.server.modelServer;

import TaskManagerMain.TaskManager.common.entity.Entity;
import TaskManagerMain.TaskManager.common.entity.Task;
import TaskManagerMain.TaskManager.common.entity.TaskImpl;
import TaskManagerMain.TaskManager.common.service.JDBCDaoTask;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
public class TaskManagerModelImplUpdateTaskTest {

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
    public void testUpdateTask() {

        model.updateTask(task);

        verify(dao).update(entityArgumentCaptor.capture());
        verify(dao, atLeastOnce()).update((Entity) task);

    }
}