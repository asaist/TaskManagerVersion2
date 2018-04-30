package labs.taskmanger.client.mainClient;


import labs.taskmanger.client.controllerClient.ClientTaskManagerController;
import labs.taskmanger.client.controllerClient.ClientTaskManagerControllerImpl;
import labs.taskmanger.client.modelClient.ClientTaskManagerModel;
import labs.taskmanger.client.modelClient.ClientTaskManagerModelImpl;
import labs.taskmanger.client.modelClient.ServerDataViewImpl;
import labs.taskmanger.client.viewClient.ClientTaskManagerView;
import labs.taskmanger.client.viewClient.ClientTaskManagerViewImpl;

import java.io.IOException;


public class ClientTaskManager {
    public static void main(String[] args) throws IOException {
        ServerDataViewImpl ServerDadaTransaction = new ServerDataViewImpl();
        ClientTaskManagerModel model = new ClientTaskManagerModelImpl(ServerDadaTransaction);
        ClientTaskManagerController controller = new ClientTaskManagerControllerImpl(model);
        ClientTaskManagerView view = new ClientTaskManagerViewImpl(controller, model);
        view.createView();
        model.addWatcher(view);

    }
}
