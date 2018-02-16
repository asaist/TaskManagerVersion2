package server.main;


import common.entity.Assignee;
import common.entity.AssigneeImpl;
import common.entity.Entity;
import common.entity.TaskImpl;
import common.service.*;
import server.controller.TaskManagerController;
import server.controller.TaskManagerControllerImpl;
import server.model.TaskManagerModel;
import server.model.TaskManagerModelImpl;
//import server.view.ClientDataViewImpl;
import server.view.ClientDataViewImpl;
import server.view.TaskManagerView;
import server.view.TaskManagerViewImpl;

import java.io.IOException;
import java.util.List;


public class ServerTaskManager {
    public static void main(String[] args) throws IOException {
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

        //в модели updateAssignee

        //TaskManagerView clientDataView = new ClientDataViewImpl(controller, model);
       // clientDataView.createView();
       // model.addWatcher(clientDataView);
    }
}
