package TaskManagerMain.TaskManager.server.modelServer;

import TaskManagerMain.TaskManager.common.entity.*;
import TaskManagerMain.TaskManager.common.service.GenericDao;
import TaskManagerMain.TaskManager.common.service.JDBCDao;
import TaskManagerMain.TaskManager.common.service.JDBCDaoTask;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.matchers.Equals;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class AddTaskTest extends Mockito{

    Entity task = new TaskImpl("1", "2", "3", "4", "4");

    @Mock
    JDBCDaoTask dao = mock(JDBCDaoTask.class);

    @InjectMocks
    TaskManagerModel model = new TaskManagerModelImpl(dao);

    @Captor
    ArgumentCaptor<TaskImpl> entityArgumentCaptor = ArgumentCaptor.forClass(TaskImpl.class);;

    @Test
    public void addTask() {

        dao.create(task);

        verify(dao).create(entityArgumentCaptor.capture());
        assertEquals("1", entityArgumentCaptor.getValue().getTaskName());

        verify(dao, atLeastOnce()).create(task);



    }

}