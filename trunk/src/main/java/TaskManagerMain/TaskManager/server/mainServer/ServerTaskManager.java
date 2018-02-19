package TaskManagerMain.TaskManager.server.mainServer;


import TaskManagerMain.TaskManager.common.entity.Assignee;
import TaskManagerMain.TaskManager.common.entity.AssigneeImpl;
import TaskManagerMain.TaskManager.common.entity.Entity;
import TaskManagerMain.TaskManager.common.entity.TaskImpl;
import TaskManagerMain.TaskManager.common.service.*;
import TaskManagerMain.TaskManager.server.controllerServer.TaskManagerController;
import TaskManagerMain.TaskManager.server.controllerServer.TaskManagerControllerImpl;
import TaskManagerMain.TaskManager.server.modelServer.TaskManagerModel;
import TaskManagerMain.TaskManager.server.modelServer.TaskManagerModelImpl;
//import server.viewServer.ClientDataViewImpl;
import TaskManagerMain.TaskManager.server.viewServer.ClientDataViewImpl;
import TaskManagerMain.TaskManager.server.viewServer.TaskManagerView;
import TaskManagerMain.TaskManager.server.viewServer.TaskManagerViewImpl;
import java.util.List;


public class ServerTaskManager {
    public static void main(String[] args) {
        GenericDao txtFileWork = new TextDao();
        JDBCDaoAssignee jdbcDaoAssignee = new JDBCDaoAssignee();
        JDBCDaoTask jdbcDaoTask = new JDBCDaoTask();
        TaskManagerModel model = new TaskManagerModelImpl(txtFileWork);
        TaskManagerController controller = new TaskManagerControllerImpl(model);
        TaskManagerView taskManagerViewImpl = new TaskManagerViewImpl(controller, model);
        taskManagerViewImpl.createView();
        model.addWatcher(taskManagerViewImpl);
        model.addAllAssignee(jdbcDaoAssignee.readAll());
        model.addAllTask(jdbcDaoTask.readAll());

        //TaskManagerView clientDataView = new ClientDataViewImpl(controllerServer, modelServer);
       // clientDataView.createView();
       // modelServer.addWatcher(clientDataView);
    }
}