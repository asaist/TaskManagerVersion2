package labs.taskmanger.common.entity;

public interface Assignee extends Entity {
    int id = 0;
    String name = null;
    String lastName = null;
    String post = null;

    void setId(int id);

    Integer getId();

    void setName(String name);

    String getName();

    void setLastName(String lastName);

    String getLastName();

    void setPost(String post);


    String getPost();


}





