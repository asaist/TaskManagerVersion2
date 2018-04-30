package labs.taskmanger.server.viewServer;

import com.google.inject.AbstractModule;

public class ViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TaskManagerView.class).to(TaskManagerViewImpl.class);
    }
}