package labs.taskmanger.server.mainServer;


import com.google.inject.Guice;
import labs.taskmanger.common.service.ServiceModule;
import labs.taskmanger.server.controllerServer.ControllerModule;
import labs.taskmanger.server.modelServer.ModelModule;
import labs.taskmanger.server.viewServer.TaskManagerView;
import labs.taskmanger.server.viewServer.ViewModule;

public class ServerTaskManager {
    public static void main(String[] args) {

        Guice.createInjector(new ViewModule(), new ControllerModule(), new ModelModule(), new ServiceModule()).getInstance(TaskManagerView.class).createView();

    }
}
