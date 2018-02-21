package labs.taskmanger.client.modelClient;

import labs.taskmanger.client.viewClient.ClientTaskManagerView;
import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.entity.Task;

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
