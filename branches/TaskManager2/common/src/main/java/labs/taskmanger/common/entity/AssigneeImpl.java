package labs.taskmanger.common.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Assignee")
public class AssigneeImpl implements Assignee, Entity {
    private int id;

    private String name;
    private String lastName;
    private String post;

    public AssigneeImpl(String name, String lastName, String post){
        this.name=name;
        this.lastName = lastName;
        this.post=post;

    }

    public AssigneeImpl(int id, String name, String lastName, String post) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.post = post;
    }

    public AssigneeImpl() {

    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement
    @Override
    public String getPost() {
        return post;
    }

    @Override
    public void setPost(String post) {
        this.post = post;
    }

    @XmlElement
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof AssigneeImpl) {
            AssigneeImpl assignee = (AssigneeImpl) anObject;

            if (!name.equals(assignee.getName())) {
                return false;
            }
            if (!lastName.equals(assignee.getLastName())) {
                return false;
            }
            if (!post.equals(assignee.getPost())) {
                return false;
            }

        }
        return true;
    }

    @Override
    public String toString() {
        return (name + " " + lastName + " " + post);
    }


}
