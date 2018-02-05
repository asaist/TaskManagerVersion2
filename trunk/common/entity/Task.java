package common.entity;

import java.util.Date;

public interface Task {
    int id = 0;
    String t_name = null;
    String description = null;
    Date deadline = null;
    String deadlineYear = null;
    String deadlineMonth = null;
    String deadlineDay = null;
    String deadlineHour = null;
    String priority = null;
    String status = null;
    String subtask = null;

    void setId(int id);

    Integer getId();

    void setT_name(String t_name);

    String getTaskName();

    void setDescription(String description);

    String getDescription();

    void setDeadlineYear(String deadlineYear);

    String getDeadlineYear();

    void setDeadlineMonth(String deadlineMonth);

    String getDeadlineMonth();

    void setDeadlineDay(String deadlineDay);

    String getDeadlineDay();

    void setDeadlineHour(String deadlineHour);

    String getDeadlineHour();

    void setPriority(String priority);

    String getPriority();

    void setStatus(String status);

    String getStatus();

    void setSubtask(String subtask);

    String getSubtask();

}
