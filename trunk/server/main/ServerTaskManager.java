package server.main;


import common.service.GenericDao;
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
        TaskManagerModel model = new TaskManagerModelImpl(txtFileWork);
        TaskManagerController controller = new TaskManagerControllerImpl(model);
        //model.addAllTask(controller.isCorrectDate(txtFileWork.readAll()));
        TaskManagerView innerView = new TaskManagerViewImpl(controller, model);

        TaskManagerView clientDataView = new ClientDataViewImpl(controller, model);

        //TaskManagerView viewClient = new ClientDataViewImpl(controller,model);
        innerView.createView();
        clientDataView.createView();
        //viewClient.createView();
        model.addWatcher(innerView);
        model.addWatcher(clientDataView);

        // model.addWatcher(viewClient);\

    }
}
