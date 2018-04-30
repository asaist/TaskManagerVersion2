package labs.taskmanger.server.ejb.task;

import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.entity.Task;
import labs.taskmanger.common.entity.TaskImpl;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoTask;
import labs.taskmanger.server.ejb.task.SearchBeanForTask;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SearchBeanForTaskImpl implements SearchBeanForTask,SessionBean {
    private String taskName;
    private String description;
    private String deadline;
    private String priority;
    private String status;

    @Override
    public List<Task> searchTaskOnJSP() {

        Task task1 = new TaskImpl();

        GenericDao taskDAO = new JDBCDaoTask();
        List<Entity> entities = taskDAO.readAll();
        List<Task> tasks = parseListEntityToListTask(entities);

        List<Task> tasks1 = new ArrayList<>();

        for (Task task:tasks){
            if (task.getTaskName().equals(taskName) && task.getStatus().equals(status)){
                task1.setId(task.getId());
                task1.setTaskName(task.getTaskName());
                task1.setDescription(task.getDescription());
                task1.setDeadline(task.getDeadline());
                task1.setPriority(task.getPriority());
                task1.setStatus(task.getStatus());

            }
        }


        tasks1.add(task1);

        return tasks1;

    }

    private List<Task> parseListEntityToListTask(List<Entity> entities) {
        Task task = new TaskImpl();
        List<Task> tasks = new ArrayList();
        for (Entity entity: entities){
            task = (Task) entity;
            tasks.add(task);

        }
        return tasks;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }



    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }
}