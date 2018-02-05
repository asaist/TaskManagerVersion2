package client.model;

import client.view.ClientTaskManagerView;
import common.entity.Assignee;
import common.entity.Entity;
import common.entity.Task;

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
