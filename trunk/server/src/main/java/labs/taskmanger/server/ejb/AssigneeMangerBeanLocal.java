package labs.taskmanger.server.ejb;

import labs.taskmanger.common.entity.Assignee;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AssigneeMangerBeanLocal {

    int getId();

    String getName();

    void setName(String name);

    String getLastName();

    void setLastName(String lastName);

    String getPost();

    void setPost(String post);

    List<Assignee> searchAssigneeOnJSP (String name, String lastName);

    void exportAssigneeToXML ();
}