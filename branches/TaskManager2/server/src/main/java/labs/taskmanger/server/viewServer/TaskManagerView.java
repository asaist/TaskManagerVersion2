package labs.taskmanger.server.viewServer;

import labs.taskmanger.server.modelServer.TaskManagerModel;

import java.util.Observer;

public interface TaskManagerView extends Observer {
    void updateViewTextConsole(String textConsole);

    void displayModels(TaskManagerModel model);

    void createView();
}
