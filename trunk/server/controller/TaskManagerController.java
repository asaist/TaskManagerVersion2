package server.controller;

import common.entity.Assignee;
import common.entity.Entity;
import common.entity.Task;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface TaskManagerController {
    void addAssignee(String name, String lastName, String post);

    void deleteAssignee(Assignee assigneeToRemove);

    void updateAssignee(Assignee assigneeToUpdate);

    void addTask(String taskName, String description, Date deadline, String priority, String status, String subtask);

    void deleteTask(Task task);

    void updateTask(Task taskToUpdate);

    List<Entity> isCorrectDate(List<Entity> entities);
}

