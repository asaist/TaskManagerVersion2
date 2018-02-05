package server.model;

import common.entity.Assignee;
import common.entity.Entity;
import common.entity.Task;
import server.view.TaskManagerView;

import java.io.IOException;
import java.util.List;

public interface TaskManagerModel {
    void create();

    void addTask(Task task);

    List<Task> getTasks();

    void addAssaignee(Assignee assignee);

    List<Assignee> getAssignees();

    void modelIsChanged();

    void deleteTask(Task taskToRemove);

    void updateTask(Task taskToUpdate);

    void addAllTask(List<Entity> entities);

    void addWatcher(TaskManagerView view);
}
