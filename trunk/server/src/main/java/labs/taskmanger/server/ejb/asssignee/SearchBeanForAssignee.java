package labs.taskmanger.server.ejb.asssignee;

import labs.taskmanger.common.entity.Assignee;

import java.util.List;

public interface SearchBeanForAssignee {

    String name = null;
    String lastName = null;
    String post = null;

    String getName();

    void setName(String name);

    String getLastName();

    void setLastName(String lastName);

    String getPost();

    void setPost(String post);

    List<Assignee> searchAssigneeOnJSP ();

    void exportAssigneeToXML ();



}