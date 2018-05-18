package labs.taskmanger.server.ejb;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;
import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;

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
public class AssigneeManagerBean implements AssigneeMangerBeanLocal {

    private int id;
    private String name;
    private String lastName;
    private String post;
    private GenericDao<Assignee> assigneeDAO;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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



    public List<Assignee> searchAssigneeOnJSP (String name, String lastName){


        assigneeDAO = new JDBCDaoAssignee();
        List<Assignee> assignees = parseListEntityToListAssignee(assigneeDAO.readAll());
        List<Assignee> assigneesResult = new ArrayList<>();

        for (Assignee assignee:assignees){
            if (assignee.getName().equals(name) && assignee.getLastName().equals(lastName)){

                assigneesResult.add((Assignee) assigneeDAO.read(assignee.getId()));
            }
        }

        return assigneesResult;
    }

    public void deleteAssigneeFromJSP (String name, String lastName, String post){

        Assignee assigneeDelete = new AssigneeImpl(name, lastName, post);
        assigneeDAO = new JDBCDaoAssignee();
        List<Assignee> assignees = parseListEntityToListAssignee(assigneeDAO.readAll());
        for (Assignee assignee:assignees){
            if (assignee.equals(assigneeDelete)){
                assigneeDAO.delete(assignee);
            }
        }

    }

    public void addAssigneeFromJSP (String name, String lastName, String post) {

        Assignee assigneeAdd = new AssigneeImpl(name, lastName, post);
        int numberEquals = 0;
        assigneeDAO = new JDBCDaoAssignee();
        List<Assignee> assignees = parseListEntityToListAssignee(assigneeDAO.readAll());
        if (assignees.size() > 0) {
            for (Assignee assignee : assignees) {
                if (assigneeAdd.equals(assignee)) {
                    System.out.println("Запись существует");
                    numberEquals++;
                }
            }

            if (numberEquals == 0) {
                assigneeDAO.create(assigneeAdd);
            }

        } else {
            assigneeDAO.create(assigneeAdd);
        }
    }



    public void exportAssigneeToXML () {


        AssigneeImpl assignee = new AssigneeImpl();
        assignee.setName(name);
        assignee.setLastName(lastName);
        assignee.setPost(post);

        try {
            marshal(assignee);
        } catch (JAXBException e) {

        }
    }

    public String marshal(AssigneeImpl assignee) throws JAXBException {
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(AssigneeImpl.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);

        QName qName = new QName("com.codenotfound.jaxb.model", "assignee");
        JAXBElement<AssigneeImpl> root = new JAXBElement<>(qName, AssigneeImpl.class, assignee);

        try {
            jaxbMarshaller.marshal(root, new FileOutputStream("exportAssignee.xml"));
        } catch (FileNotFoundException e) {

        }

        String result = stringWriter.toString();
        return result;
    }


    private List<Assignee> parseListEntityToListAssignee(List<Entity> entities) {
        Assignee assignee = new AssigneeImpl();
        List<Assignee> assignees = new ArrayList();
        for (Entity entity: entities){
            assignee = (Assignee) entity;
            assignees.add(assignee);

        }
        return assignees;
    }


}