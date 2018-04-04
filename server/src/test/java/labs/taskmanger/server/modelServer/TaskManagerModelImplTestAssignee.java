package labs.taskmanger.server.modelServer;

import labs.taskmanger.common.entity.*;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.common.service.JDBCDaoTask;
import labs.taskmanger.server.modelServer.TaskManagerModel;
import labs.taskmanger.server.modelServer.TaskManagerModelImpl;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import static org.junit.Assert.*;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskManagerModelImplTestAssignee {

    Assignee assignee;
    JDBCDaoAssignee daoAssignee;
    JDBCDaoTask daoTask;
    TaskManagerModel model;
    String regexp;
    String name;


    @Before
    public void inicialize(){
        regexp = "1";
        name = "1";
        assignee = new AssigneeImpl("1", "2", "3");
        daoAssignee = mock(JDBCDaoAssignee.class);
        daoTask = mock(JDBCDaoTask.class);
        model = new TaskManagerModelImpl(daoTask, daoAssignee);
    }

    @Test
    public void testAddAssignee() {

        model.addAssaignee(assignee);

        verify(daoAssignee).create(assignee);
        assertThat(assignee, hasName());
        verify(daoAssignee, atLeastOnce()).create(assignee);
        verify(daoAssignee).create(argThat(hasName()));

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

    private Matcher<Assignee> hasName() {

        return new TypeSafeDiagnosingMatcher<Assignee>() {
            @Override
            protected boolean matchesSafely(Assignee assignee, Description description) {
                return assignee.getName().equals(name);
            }

            public void describeTo(Description description) {
                description.appendText("error");

            }
        };
    }

}