package labs.taskmanger.server.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@NamedQueries(@NamedQuery(name = "Assignee.getAll", query = "SELECT e FROM Assignee e"))
public class Assignee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String post;


    public Assignee(int id, String name, String lastName, String post) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.post = post;
    }

    public Assignee (){}

    public Integer getId() { return id;  }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

}