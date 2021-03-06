package labs.taskmanger.server.controllerServer;

import com.google.inject.AbstractModule;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TaskManagerController.class).to(TaskManagerControllerImpl.class);
    }
}