package labs.taskmanger.server.controllerServer;


import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.Task;

public interface TaskManagerController {
    void addAssignee(String name, String lastName, String post);

    void deleteAssignee(Assignee assigneeToRemove);

    void updateAssignee(Assignee assigneeToUpdate);

    void addTask(String taskName, String description, String deadline, String priority, String status);

    void deleteTask(Task task);

    void updateTask(Task taskToUpdate);

}

