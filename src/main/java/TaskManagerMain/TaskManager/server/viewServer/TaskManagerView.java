package TaskManagerMain.TaskManager.server.viewServer;

import TaskManagerMain.TaskManager.server.modelServer.TaskManagerModel;

import java.io.IOException;
import java.util.Observer;

public interface TaskManagerView extends Observer {
    void updateViewTextConsole(String textConsole);

    void displayModels(TaskManagerModel model);

    void createView();
}
