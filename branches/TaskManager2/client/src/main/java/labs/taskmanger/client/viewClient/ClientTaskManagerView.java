package labs.taskmanger.client.viewClient;

import labs.taskmanger.client.modelClient.ClientTaskManagerModel;

import java.util.Observer;

public interface ClientTaskManagerView extends Observer {
    void updateViewTextConsole(String textConsole);

    void displayModels(ClientTaskManagerModel model);

    void createView();
}
