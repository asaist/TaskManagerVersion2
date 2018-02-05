package client.controller;

import common.entity.Entity;
import common.entity.Task;

import java.io.IOException;
import java.util.List;

public interface ClientTaskManagerController {
    void addAssignee(String name, String lastName, String post);

    void addTask(String t_name, String description, String deadlineYear, String deadlineMonth, String deadlineDay, String deadlineHour, String priority, String status, String subtask);

    void deleteTask(Task task) throws IOException;

    void updateTask(Task taskToUpdate) throws IOException;

    List<Entity> isCorrectDate(List<Entity> entities);
}

