package labs.taskmanger.server.mainServer;


import com.google.inject.Guice;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.server.controllerServer.ControllerModule;
import labs.taskmanger.server.controllerServer.TaskManagerController;
import labs.taskmanger.server.controllerServer.TaskManagerControllerImpl;
import labs.taskmanger.server.modelServer.ModelModule;
import labs.taskmanger.server.modelServer.TaskManagerModel;
import labs.taskmanger.server.modelServer.TaskManagerModelImpl;
import labs.taskmanger.server.viewServer.TaskManagerView;
import labs.taskmanger.server.viewServer.TaskManagerViewImpl;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoTask;
import labs.taskmanger.server.viewServer.ViewModule;


public class ServerTaskManager {
    public static void main(String[] args) {

        Guice.createInjector(new ModelModule(), new ViewModule(), new ControllerModule()).getInstance(TaskManagerView.class).createView();

    }
}
