package labs.taskmanger.server.ejb;

import labs.taskmanger.common.entity.*;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.common.service.JDBCDaoTask;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TaskManagerBean implements TaskManagerBeanLocal {

    Integer id;
    String taskName;
    String description;
    String deadline;
    String priority;
    String status;
    private GenericDao<Task> taskDAO;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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
    public String getDeadline() {
        return deadline;
    }

    @Override
    public void setDeadline(String deadline) {
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

    public List<Task> searchTaskOnJSP (String name, String status){


        taskDAO = new JDBCDaoTask();
        List<Task> tasks = parseListEntityToListTask(taskDAO.readAll());
        List<Task> tasksResult = new ArrayList<>();

        for (Task task:tasks){
            if (task.getTaskName().equals(name) && task.getStatus().equals(status)){

                tasksResult.add((Task) taskDAO.read(task.getId()));
            }
        }

        return tasksResult;
    }

    public void deleteTaskFromJSP (String taskName, String description, String deadline, String priority, String status){

        Task taskDelete = new TaskImpl(taskName, description,deadline, priority, status);
        taskDAO = new JDBCDaoTask();
        List<Task> tasks = parseListEntityToListTask(taskDAO.readAll());
        for (Task task : tasks){
            if (task.equals(taskDelete)){
                taskDAO.delete(task);
            }
        }

    }

    public void addTaskFromJSP (String taskName, String description, String deadline, String priority, String status) {

        Task taskAdd = new TaskImpl(taskName, description, deadline, priority, status);
        taskDAO = new JDBCDaoTask();
        int numberEquals = 0;
        List<Task> tasks = parseListEntityToListTask(taskDAO.readAll());
        if (tasks.size() > 0) {
            for (Task task : tasks){
                if (taskAdd.equals(task)) {
                    System.out.println("Запись существует");
                    numberEquals++;

                }
            }

            if (numberEquals == 0) {
                taskDAO.create(taskAdd);
            }

        } else {
            taskDAO.create(taskAdd);
        }
    }



    public void exportTaskToXML () {


        TaskImpl task = new TaskImpl(taskName, description, deadline, priority, status);

        try {
            marshal(task);
        } catch (JAXBException e) {

        }
    }

    public String marshal(TaskImpl task) throws JAXBException {
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(TaskImpl.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);

        QName qName = new QName("com.codenotfound.jaxb.model", "task");
        JAXBElement<TaskImpl> root = new JAXBElement<>(qName, TaskImpl.class, task);

        try {
            jaxbMarshaller.marshal(root, new FileOutputStream("exportTask.xml"));
        } catch (FileNotFoundException e) {

        }

        String result = stringWriter.toString();
        return result;
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

}