package TaskManagerMain.TaskManager.common.entity;

import java.time.LocalDateTime;
import java.util.Date;

public interface Task {
    Integer id = 0;
    String t_name = null;
    String description = null;
    String deadline = null;
    String priority = null;
    String status = null;

    void setId(int id);

    Integer getId();

    void setTaskName(String taskName);

    String getTaskName();

    void setDescription(String description);

    String getDescription();

    void setDeadline(String deadline);

    String getDeadline();

    void setPriority(String priority);

    String getPriority();

    void setStatus(String status);

    String getStatus();

}
