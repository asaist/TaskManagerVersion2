package TaskManagerMain.TaskManager.client.modelClient;

import TaskManagerMain.TaskManager.client.viewClient.ClientTaskManagerView;
import TaskManagerMain.TaskManager.common.entity.Assignee;
import TaskManagerMain.TaskManager.common.entity.Entity;
import TaskManagerMain.TaskManager.common.entity.Task;

import java.util.List;

public interface ClientTaskManagerModel {
    void create();

    void addTask(Task task);

    List<Task> getTasks();

    void addAssaignee(Assignee assignee);

    List<Assignee> getAssignees();

    void modelIsChanged();

    void deleteTask(Task taskToRemove);

    void updateTask(Task taskToUpdate);

    void addAllTask(List<Entity> entities);

    void addWatcher(ClientTaskManagerView view);
}
