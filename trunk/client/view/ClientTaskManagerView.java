package client.view;

import client.model.ClientTaskManagerModel;

import java.util.Observer;

public interface ClientTaskManagerView extends Observer {
    void updateViewTextConsole(String textConsole);

    void displayModels(ClientTaskManagerModel model);

    void createView();
}
