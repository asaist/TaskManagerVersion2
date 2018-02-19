package TaskManagerMain.TaskManager.client.mainClient;


import TaskManagerMain.TaskManager.client.controllerClient.ClientTaskManagerController;
import TaskManagerMain.TaskManager.client.controllerClient.ClientTaskManagerControllerImpl;
import TaskManagerMain.TaskManager.client.modelClient.ClientTaskManagerModel;
import TaskManagerMain.TaskManager.client.modelClient.ClientTaskManagerModelImpl;
import TaskManagerMain.TaskManager.client.modelClient.ServerDataViewImpl;
import TaskManagerMain.TaskManager.client.viewClient.ClientTaskManagerView;
import TaskManagerMain.TaskManager.client.viewClient.ClientTaskManagerViewImpl;
import TaskManagerMain.TaskManager.common.service.GenericDao;
import TaskManagerMain.TaskManager.common.service.TextDao;
import TaskManagerMain.TaskManager.server.controllerServer.TaskManagerController;
import TaskManagerMain.TaskManager.server.controllerServer.TaskManagerControllerImpl;
import TaskManagerMain.TaskManager.server.modelServer.TaskManagerModel;
import TaskManagerMain.TaskManager.server.modelServer.TaskManagerModelImpl;
//import server.viewServer.ClientDataViewImpl;
import TaskManagerMain.TaskManager.server.viewServer.TaskManagerView;
import TaskManagerMain.TaskManager.server.viewServer.TaskManagerViewImpl;

import java.io.IOException;
import java.util.List;


public class ClientTaskManager {
    public static void main(String[] args) throws IOException {
        ServerDataViewImpl ServerDadaTransaction = new ServerDataViewImpl();
        ClientTaskManagerModel model = new ClientTaskManagerModelImpl(ServerDadaTransaction);
        ClientTaskManagerController controller = new ClientTaskManagerControllerImpl(model);
        //modelServer.addAllTask(controllerServer.isCorrectDate(ServerDadaTransaction.readAll()));
        ClientTaskManagerView view = new ClientTaskManagerViewImpl(controller, model);
        view.createView();
        model.addWatcher(view);

    }
}
