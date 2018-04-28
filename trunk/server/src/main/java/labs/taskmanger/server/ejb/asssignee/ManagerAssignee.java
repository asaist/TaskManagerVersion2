package labs.taskmanger.server.ejb.asssignee;

import labs.taskmanger.common.entity.Assignee;

import javax.ejb.EJBObject;
import java.util.List;

public interface ManagerAssignee extends EJBObject {

    int getId();

    String getName();

    void setName(String name);

    String getLastName();

    void setLastName(String lastName);

    String getPost();

    void setPost(String post);

    List<Assignee> searchAssigneeOnJSP ();

    void exportAssigneeToXML ();
}