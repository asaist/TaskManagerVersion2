package TaskManagerMain.TaskManager.server.modelServer;

import TaskManagerMain.TaskManager.common.entity.Assignee;
import TaskManagerMain.TaskManager.common.entity.AssigneeImpl;
import TaskManagerMain.TaskManager.common.entity.Entity;
import TaskManagerMain.TaskManager.common.service.JDBCDaoAssignee;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskManagerModelImplAddAssigneeTest {

    Assignee assignee = new AssigneeImpl("1", "2", "3");
    JDBCDaoAssignee dao = mock(JDBCDaoAssignee.class);
    TaskManagerModel model = new TaskManagerModelImpl(dao, dao);

    @Captor
    ArgumentCaptor<AssigneeImpl> entityArgumentCaptor = ArgumentCaptor.forClass(AssigneeImpl.class);;

    @Test
    public void testAddAssignee() {

        model.addAssaignee(assignee);

        verify(dao).create(entityArgumentCaptor.capture());
        assertEquals("1", entityArgumentCaptor.getValue().getName());
        verify(dao, atLeastOnce()).create((Entity) assignee);

    }
}