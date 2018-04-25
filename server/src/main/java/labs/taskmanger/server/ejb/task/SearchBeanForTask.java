package labs.taskmanger.server.ejb.task;

import labs.taskmanger.common.entity.Task;

import java.util.List;

public interface SearchBeanForTask {
    String taskName = null;
    String description = null;
    String deadline = null;
    String priority = null;
    String status = null;

    String getStatus();

    void setStatus(String status);

    String getTaskName();

    void setTaskName(String taskName);

    String getDescription();

    void setDescription(String description);

    String getDeadline();

    void setDeadline(String deadline);

    String getPriority();

    void setPriority(String priority);

    List<Task> searchTaskOnJSP ();
}