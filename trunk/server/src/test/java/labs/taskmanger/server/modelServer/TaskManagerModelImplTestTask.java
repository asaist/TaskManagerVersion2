package labs.taskmanger.server.modelServer;

import labs.taskmanger.common.entity.*;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.common.service.JDBCDaoTask;
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

public class TaskManagerModelImplTestTask {

    Task task;
    JDBCDaoAssignee daoAssignee;
    JDBCDaoTask daoTask;
    TaskManagerModel model;
    String regexp;
    String name;


    @Before
    public void inicialize(){
        regexp = "1";
        name = "1";
        task = new TaskImpl("1", "2", "3", "4", "4");
        daoAssignee = mock(JDBCDaoAssignee.class);
        daoTask = mock(JDBCDaoTask.class);
        model = new TaskManagerModelImpl(daoTask, daoAssignee);
    }

    @Test
    public void testAddTask() {

        model.addTask(task);

        verify(daoTask).create(task);
        assertThat(task, hasName());
        verify(daoTask, atLeastOnce()).create(task);
        verify(daoTask).create(argThat(hasName()));

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

    private Matcher<Task> hasName() {

        return new TypeSafeDiagnosingMatcher<Task>() {
            @Override
            protected boolean matchesSafely(Task task, Description description) {
                return task.getTaskName().equals(name);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("error");

            }
        };
    }

}