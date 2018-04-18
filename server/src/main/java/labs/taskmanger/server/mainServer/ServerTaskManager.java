package labs.taskmanger.server.mainServer;


import com.google.inject.Guice;
import com.google.inject.servlet.ServletModule;
import labs.taskmanger.common.service.ServiceModule;
import labs.taskmanger.server.controllerServer.ControllerModule;
import labs.taskmanger.server.modelServer.ModelModule;
import labs.taskmanger.server.viewServer.TaskManagerView;
import labs.taskmanger.server.viewServer.ViewModule;
import labs.taskmanger.server.web.ServletTaskManager;

public class ServerTaskManager {
    public static void main(String[] args) {

        Guice.createInjector(new ViewModule(), new ControllerModule(), new ModelModule(), new ServiceModule(), new ServletModule() {
            @Override
            protected void configureServlets() {
                serve("*.jsp").with(ServletTaskManager.class);
            }
        }).getInstance(TaskManagerView.class).createView();

    }
}
