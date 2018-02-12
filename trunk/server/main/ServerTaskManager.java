package server.main;


import common.entity.Assignee;
import common.entity.AssigneeImpl;
import common.entity.Entity;
import common.entity.TaskImpl;
import common.service.GenericDao;
import common.service.JDBCDao;
import common.service.JDBCDaoAssignee;
import common.service.TextDao;
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
        JDBCDaoAssignee jdbcDao = new JDBCDaoAssignee();
        TaskManagerModel model = new TaskManagerModelImpl(txtFileWork);
        model.addAllAssignee();
        TaskManagerController controller = new TaskManagerControllerImpl(model);
        TaskManagerView taskManagerViewImpl = new TaskManagerViewImpl(controller, model);
        //TaskManagerView clientDataView = new ClientDataViewImpl(controller, model);
        taskManagerViewImpl.createView();
       // clientDataView.createView();
        model.addWatcher(taskManagerViewImpl);
       // model.addWatcher(clientDataView);
    }
}
