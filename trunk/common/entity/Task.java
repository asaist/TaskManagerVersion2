package common.entity;

import java.util.Date;

public interface Task {
    Integer id = 0;
    String t_name = null;
    String description = null;
    Date deadline = null;
    String priority = null;
    String status = null;
    String subtask = null;

    void setId(int id);

    Integer getId();

    void setTaskName(String taskName);

    String getTaskName();

    void setDescription(String description);

    String getDescription();

    void setDeadline(Date deadline);

    Date getDeadline();

    void setPriority(String priority);

    String getPriority();

    void setStatus(String status);

    String getStatus();

    void setSubtask(String subtask);

    String getSubtask();

}
