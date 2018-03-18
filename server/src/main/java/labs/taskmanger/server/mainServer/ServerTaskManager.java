package labs.taskmanger.server.mainServer;


import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.server.controllerServer.TaskManagerController;
import labs.taskmanger.server.controllerServer.TaskManagerControllerImpl;
import labs.taskmanger.server.modelServer.TaskManagerModel;
import labs.taskmanger.server.modelServer.TaskManagerModelImpl;
import labs.taskmanger.server.viewServer.TaskManagerView;
import labs.taskmanger.server.viewServer.TaskManagerViewImpl;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoTask;


public class ServerTaskManager {
    public static void main(String[] args) {
        GenericDao jdbcDaoAssignee = new JDBCDaoAssignee();
        GenericDao jdbcDaoTask = new JDBCDaoTask();
        TaskManagerModel model = new TaskManagerModelImpl(jdbcDaoTask, jdbcDaoAssignee);
        TaskManagerController controller = new TaskManagerControllerImpl(model);
        TaskManagerView taskManagerViewImpl = new TaskManagerViewImpl(controller, model);

        taskManagerViewImpl.createView();
        model.addWatcher(taskManagerViewImpl);
        model.addAllAssignee();
        model.addAllTask();

    }
}
