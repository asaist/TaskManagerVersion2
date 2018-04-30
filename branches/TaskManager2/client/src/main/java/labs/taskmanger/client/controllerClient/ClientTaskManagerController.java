package labs.taskmanger.client.controllerClient;

import labs.taskmanger.common.entity.Task;

import java.util.Date;

public interface ClientTaskManagerController {
    void addAssignee(String name, String lastName, String post);

    void addTask(String t_name, String description, String deadline, String priority, String status, String subtask);

    void deleteTask(Task task);

    void updateTask(Task taskToUpdate);

    Date stringToDateTask(String taskDeadline);

}

