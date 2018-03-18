package labs.taskmanger.server.controllerServer;

import labs.taskmanger.common.entity.Task;
import labs.taskmanger.common.entity.TaskImpl;
import labs.taskmanger.server.modelServer.TaskManagerModel;
import labs.taskmanger.server.viewServer.TaskManagerView;
import labs.taskmanger.server.viewServer.TaskManagerViewImpl;
import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TaskManagerControllerImpl implements TaskManagerController {
    TaskManagerModel model;
    TaskManagerView view;
    TaskManagerController controller;

    public TaskManagerControllerImpl(TaskManagerModel model) {
        this.model = model;
        view = new TaskManagerViewImpl(this, model);

    }

    //Task

    public void addTask(String taskName, String description, String deadline, String priority, String status) {
        Task task = new TaskImpl(taskName, description, deadline, priority, status);
        checkFieldsTask(task);
        model.addTask(task);
    }

    public void deleteTask(Task taskToRemove) {
        checkFieldsTask(taskToRemove);
        model.deleteTask(taskToRemove);
    }

    public void updateTask(Task taskToUpdate) {
        checkFieldsTask(taskToUpdate);
        model.updateTask(taskToUpdate);
    }

    public void checkFieldsTask(Task task) {


        if (isCorrect(task.getTaskName())) {
            throw new RuntimeException("Task name is not correct");
        } else {
            task.setTaskName(task.getTaskName().trim());
        }
        if (isCorrect(task.getDescription())) {
            throw new RuntimeException("Description is not correct");
        } else {
            task.setDescription(task.getDescription().trim());
        }
        if (isCorrect(String.valueOf(task.getDeadline()))) {
            throw new RuntimeException("Year is not correct");
        } else {
            task.setDeadline(task.getDeadline());
        }

        if (isCorrect(task.getPriority())) {
            throw new RuntimeException("Priority is not correct");
        } else {
            task.setPriority(task.getPriority().trim());
        }
        if (isCorrect(task.getStatus())) {
            throw new RuntimeException("Status is not correct");
        } else {
            task.setStatus(task.getStatus().trim());
        }
    }

    // }

    //Assaignee
    public void addAssignee(String name, String lastName, String post) {
        Assignee assignee = new AssigneeImpl(name, lastName , post);
        checkFieldsAssignee(assignee);
        model.addAssaignee(assignee);
    }

    public void deleteAssignee(Assignee assigneeToRemove) {
        checkFieldsAssignee(assigneeToRemove);
        model.deleteAssignee(assigneeToRemove);
    }

    public void updateAssignee(Assignee assigneeToUpdate) {
        checkFieldsAssignee(assigneeToUpdate);
        model.updateAssignee(assigneeToUpdate);
    }




    public void checkFieldsAssignee(Assignee assignee) {

        if (isCorrect(assignee.getName())) {
            throw new RuntimeException("Name is not correct");
        } else {
            assignee.setName(assignee.getName().trim());
        }

        if (isCorrect(assignee.getLastName())) {
            throw new RuntimeException("Last name is not correct");
        } else {
            assignee.setLastName(assignee.getLastName().trim());
        }

        if (isCorrect(assignee.getPost())) {
            throw new RuntimeException("Post is not correct");
        } else {
            assignee.setPost(assignee.getPost().trim());
        }
    }

    private boolean isCorrect(String field) {
        return field == null || field.isEmpty() || field.trim().isEmpty() || field.indexOf(";") != -1;
    }

    public LocalDateTime stringToDateTask(String taskDeadline) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(taskDeadline, formatter);

        return formatDateTime;
    }

}


