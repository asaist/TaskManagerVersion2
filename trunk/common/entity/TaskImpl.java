package common.entity;



public class TaskImpl implements Task, Entity {
    private int id;
    private static int idInc;
    private String t_name;
    private String description;
    private String deadlineYear;
    private String deadlineMonth;
    private String deadlineDay;
    private String deadlineHour;
    private String priority;
    private String status;
    private String subtask;


    public TaskImpl(String t_name, String description, String deadlineYear, String deadlineMonth, String deadlineDay, String deadlineHour, String priority, String status, String subtask) {
        this.t_name = t_name;
        this.description = description;
        this.deadlineYear = deadlineYear;
        this.deadlineMonth = deadlineMonth;
        this.deadlineDay = deadlineDay;
        this.deadlineHour = deadlineHour;
        this.priority = priority;
        this.status = status;
        this.subtask = subtask;
    }

    public TaskImpl() {
        this.id = idInc++;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getDeadlineYear() {
        return deadlineYear;
    }

    @Override
    public void setDeadlineYear(String deadlineYear) {
        this.deadlineYear = deadlineYear;
    }

    @Override
    public String getDeadlineMonth() {
        return deadlineMonth;
    }

    @Override
    public void setDeadlineMonth(String deadlineMonth) {
        this.deadlineMonth = deadlineMonth;
    }

    @Override
    public String getDeadlineDay() {
        return deadlineDay;
    }

    @Override
    public void setDeadlineDay(String deadlineDay) {
        this.deadlineDay = deadlineDay;
    }

    @Override
    public String getDeadlineHour() {
        return deadlineHour;
    }

    @Override
    public void setDeadlineHour(String deadlineHour) {
        this.deadlineHour = deadlineHour;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubtask() {
        return subtask;
    }

    public void setSubtask(String subtask) {
        this.subtask = subtask;
    }


    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof TaskImpl) {
            TaskImpl task = (TaskImpl) anObject;
                /*if (name == null || assignee.getName() == null)  {
                    return false;
                }*/

            if (!t_name.equals(task.getTaskName())) {
                return false;
            }
            if (!description.equals(task.getDescription())) {
                return false;
            }
            if (!deadlineYear.equals(task.getDeadlineYear())) {
                return false;
            }
            if (!deadlineMonth.equals(task.getDeadlineMonth())) {
                return false;
            }
            if (!deadlineDay.equals(task.getDeadlineDay())) {
                return false;
            }
            if (!deadlineHour.equals(task.getDeadlineHour())) {
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
        return (id + ";" + t_name + ";" + description + ";" + deadlineYear + ";" + deadlineMonth + ";" + deadlineDay + ";" + deadlineHour + ";" + priority + ";" + status + ";" + subtask);

    }


}
