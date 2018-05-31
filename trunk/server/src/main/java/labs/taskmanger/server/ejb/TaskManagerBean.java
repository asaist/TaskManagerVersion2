package labs.taskmanger.server.ejb;

import labs.taskmanger.common.entity.*;
import labs.taskmanger.common.service.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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


       // taskDAO = new JDBCDaoTask();
        taskDAO = new EAVCRDaoTask();
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
        // taskDAO = new JDBCDaoTask();
        taskDAO = new EAVCRDaoTask();
        List<Task> tasks = parseListEntityToListTask(taskDAO.readAll());
        for (Task task : tasks){
            if (task.equals(taskDelete)){
                taskDAO.delete(task);
            }
        }

    }

    public void addTaskFromJSP (String taskName, String description, String deadline, String priority, String status) {

        Task taskAdd = new TaskImpl(taskName, description, deadline, priority, status);
        // taskDAO = new JDBCDaoTask();
        taskDAO = new EAVCRDaoTask();
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



    public void taskToXML (List<Task> tasks) throws ParserConfigurationException, TransformerException {


        File destFile = new File("C:\\Export\\");

        destFile.mkdirs();

        destFile = new File("C:\\Export\\ExportTask.xml");

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("ListTask");
            doc.appendChild(rootElement);

            Integer id = 1;

            for (Task taskFromList:tasks) {

                Element task = doc.createElement("Task");
                rootElement.appendChild(task);

                Attr attr = doc.createAttribute("id");
                attr.setValue(id.toString());
                task.setAttributeNode(attr);

                id++;


                Element taskName = doc.createElement("TaskName");
                taskName.appendChild(doc.createTextNode(taskFromList.getTaskName()));
                task.appendChild(taskName);

                Element description = doc.createElement("Description");
                description.appendChild(doc.createTextNode(taskFromList.getDescription()));
                task.appendChild(description);

                Element deadline = doc.createElement("Deadline");
                deadline.appendChild(doc.createTextNode(taskFromList.getDeadline()));
                task.appendChild(deadline);

                Element priority = doc.createElement("Priority");
                priority.appendChild(doc.createTextNode(taskFromList.getPriority()));
                task.appendChild(priority);

                Element status = doc.createElement("Status");
                status.appendChild(doc.createTextNode(taskFromList.getStatus()));
                task.appendChild(status);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty (OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(destFile);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void inicialize (GenericDao<Task> taskDao) {
        this.taskDAO = taskDao;
    }


    public List<Task> parseListEntityToListTask(List<Entity> entities) {
        Task task = new TaskImpl();
        List<Task> tasks = new ArrayList();
        for (Entity entity: entities){
            task = (Task) entity;
            tasks.add(task);

        }
        return tasks;
    }

}