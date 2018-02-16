package client.controller;

import common.entity.Entity;
import common.entity.Task;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ClientTaskManagerController {
    void addAssignee(String name, String lastName, String post);

    void addTask(String t_name, String description, String deadline, String priority, String status, String subtask);

    void deleteTask(Task task);

    void updateTask(Task taskToUpdate);

    Date stringToDateTask(String taskDeadline);

}

