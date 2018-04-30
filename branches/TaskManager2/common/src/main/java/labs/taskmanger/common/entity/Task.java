package labs.taskmanger.common.entity;

public interface Task extends Entity {
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
