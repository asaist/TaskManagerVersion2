package labs.taskmanger.server.ejb;

import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.entity.Task;
import labs.taskmanger.common.service.GenericDao;

import javax.ejb.Local;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

@Local
public interface TaskManagerBeanLocal {

    void setId(Integer id);

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

    List<Task> searchTaskOnJSP (String name, String status);

    void deleteTaskFromJSP (String taskName, String description, String deadline, String priority, String status);

    void addTaskFromJSP (String taskName, String description, String deadline, String priority, String status);

    void taskToXML (List<Task> tasks) throws ParserConfigurationException, TransformerException;

    void inicialize (GenericDao<Task> taskDao);

    List<Task> parseListEntityToListTask(List<Entity> entities);
}