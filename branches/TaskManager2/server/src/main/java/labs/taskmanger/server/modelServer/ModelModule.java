package labs.taskmanger.server.modelServer;

import com.google.inject.AbstractModule;

public class ModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TaskManagerModel.class).to(TaskManagerModelImpl.class);
    }
}