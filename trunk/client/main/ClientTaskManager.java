package client.main;


import client.controller.ClientTaskManagerController;
import client.controller.ClientTaskManagerControllerImpl;
import client.model.ClientTaskManagerModel;
import client.model.ClientTaskManagerModelImpl;
import client.model.ServerDataViewImpl;
import client.view.ClientTaskManagerView;
import client.view.ClientTaskManagerViewImpl;
import common.service.GenericDao;
import common.service.TextDao;
import server.controller.TaskManagerController;
import server.controller.TaskManagerControllerImpl;
import server.model.TaskManagerModel;
import server.model.TaskManagerModelImpl;
//import server.view.ClientDataViewImpl;
import server.view.TaskManagerView;
import server.view.TaskManagerViewImpl;

import java.io.IOException;
import java.util.List;


public class ClientTaskManager {
    public static void main(String[] args) throws IOException {
        ServerDataViewImpl ServerDadaTransaction = new ServerDataViewImpl();
        ClientTaskManagerModel model = new ClientTaskManagerModelImpl(ServerDadaTransaction);
        ClientTaskManagerController controller = new ClientTaskManagerControllerImpl(model);
        //model.addAllTask(controller.isCorrectDate(ServerDadaTransaction.readAll()));
        ClientTaskManagerView view = new ClientTaskManagerViewImpl(controller, model);
        view.createView();
        model.addWatcher(view);

    }
}
