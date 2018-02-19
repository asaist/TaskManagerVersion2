package TaskManagerMain.TaskManager.server.controllerServer;

import TaskManagerMain.TaskManager.common.entity.Assignee;
import TaskManagerMain.TaskManager.common.entity.Task;

import java.time.LocalDateTime;
import java.util.Date;

public interface TaskManagerController {
    void addAssignee(String name, String lastName, String post);

    void deleteAssignee(Assignee assigneeToRemove);

    void updateAssignee(Assignee assigneeToUpdate);

    void addTask(String taskName, String description, String deadline, String priority, String status);

    void deleteTask(Task task);

    void updateTask(Task taskToUpdate);

    LocalDateTime stringToDateTask(String taskDeadline);
}

