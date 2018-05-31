package labs.taskmanger.common.service;

import com.google.inject.TypeLiteral;
import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.entity.Task;
import com.google.inject.AbstractModule;

import static jdk.nashorn.internal.objects.NativeFunction.bind;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<GenericDao<Task>>(){}).to(new TypeLiteral<JDBCDaoTask>() {});
        bind(new TypeLiteral<GenericDao<Assignee>>(){}).to(new TypeLiteral<JDBCDaoAssignee>() {});
    }
}