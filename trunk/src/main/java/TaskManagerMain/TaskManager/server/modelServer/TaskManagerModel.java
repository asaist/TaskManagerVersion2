package TaskManagerMain.TaskManager.server.modelServer;

import TaskManagerMain.TaskManager.common.entity.Assignee;
import TaskManagerMain.TaskManager.common.entity.Entity;
import TaskManagerMain.TaskManager.common.entity.Task;
import TaskManagerMain.TaskManager.server.viewServer.TaskManagerView;

import java.io.IOException;
import java.util.List;

public interface TaskManagerModel {
    void create();

    void addTask(Task task);

    List<Task> getTasks();

    void addAssaignee(Assignee assignee);

    List<Assignee> getAssignees();

    void modelIsChanged();

    void addAllAssignee(List<Entity> assignees1);

    void deleteAssignee(Assignee assigneeToRemove);

    void updateAssignee (Assignee assigneeToUpdate);

    void deleteTask(Task taskToRemove);

    void updateTask(Task taskToUpdate);

    void addAllTask(List<Entity> entities);

    void addWatcher(TaskManagerView view);

}
