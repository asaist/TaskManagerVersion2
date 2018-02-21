package labs.taskmanger.server.modelServer;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.Task;
import labs.taskmanger.server.viewServer.TaskManagerView;

import java.util.List;

public interface TaskManagerModel {
    void create();

    void addTask(Task task);

    List<Task> getTasks();

    void addAssaignee(Assignee assignee);

    List<Assignee> getAssignees();

    void modelIsChanged();

    void addAllAssignee();

    void deleteAssignee(Assignee assigneeToRemove);

    void updateAssignee (Assignee assigneeToUpdate);

    void deleteTask(Task taskToRemove);

    void updateTask(Task taskToUpdate);

    void addAllTask();

    void addWatcher(TaskManagerView view);

}
