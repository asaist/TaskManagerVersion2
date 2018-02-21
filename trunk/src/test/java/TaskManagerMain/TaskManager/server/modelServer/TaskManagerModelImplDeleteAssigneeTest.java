package TaskManagerMain.TaskManager.server.modelServer;

import TaskManagerMain.TaskManager.common.entity.Assignee;
import TaskManagerMain.TaskManager.common.entity.AssigneeImpl;
import TaskManagerMain.TaskManager.common.entity.Entity;
import TaskManagerMain.TaskManager.common.service.JDBCDaoAssignee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskManagerModelImplDeleteAssigneeTest {

    Assignee assignee;
    JDBCDaoAssignee dao;
    TaskManagerModel model;

    @Before
    public void inicialize(){
        assignee = new AssigneeImpl("1", "2", "3");
        dao = mock(JDBCDaoAssignee.class);
        model = new TaskManagerModelImpl(dao, dao);

    }

    @Captor
    ArgumentCaptor<AssigneeImpl> entityArgumentCaptor = ArgumentCaptor.forClass(AssigneeImpl.class);;

    @Test
    public void testDeleteAssignee() {

        model.deleteAssignee(assignee);

        verify(dao).delete(entityArgumentCaptor.capture());
        verify(dao, atLeastOnce()).delete((Entity) assignee);

    }
}