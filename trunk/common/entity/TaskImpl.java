package common.entity;


import java.util.Date;

public class TaskImpl implements Task, Entity {
    private int id;
    private static int idInc;
    private String taskName;
    private String description;
    private Date deadline;
    private String priority;
    private String status;
    private String subtask;

    public TaskImpl() {

    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public static int getIdInc() {
        return idInc;
    }

    public static void setIdInc(int idInc) {
        TaskImpl.idInc = idInc;
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Date getDeadline() {
        return deadline;
    }

    @Override
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String getPriority() {
        return priority;
    }

    @Override
    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getSubtask() {
        return subtask;
    }

    @Override
    public void setSubtask(String subtask) {
        this.subtask = subtask;
    }





    public TaskImpl(String taskName, String description, Date deadline, String priority, String status, String subtask) {
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
        this.subtask = subtask;
    }


    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof TaskImpl) {
            TaskImpl task = (TaskImpl) anObject;

            if (!taskName.equals(task.getTaskName())) {
                return false;
            }
            if (!description.equals(task.getDescription())) {
                return false;
            }
            if (!deadline.equals(task.getDeadline())) {
                return false;
            }
            if (!priority.equals(task.getPriority())) {
                return false;
            }
            if (!status.equals(task.getStatus())) {
                return false;
            }
            if (!subtask.equals(task.getSubtask())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return (taskName + " " + description + " " + deadline.getYear() + " " + deadline.getMonth() + " " + deadline.getDay() + " " + deadline.getHours() + " " + priority + " " + status + " " + subtask);

    }

}
