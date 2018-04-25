package labs.taskmanger.server.ejb.asssignee;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;
import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.server.ejb.asssignee.SearchBeanForAssignee;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SearchBeanForAssigneeImpl implements SessionBean, SearchBeanForAssignee {

    private String name;
    private String lastName;
    private String post;

    public SearchBeanForAssigneeImpl(){}

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



    public List<Assignee> searchAssigneeOnJSP (){

        Assignee assignee1 = new AssigneeImpl();
        GenericDao assigneeDAO = new JDBCDaoAssignee();
        List<Entity> entities = assigneeDAO.readAll();
        List<Assignee> assignees = parseListEntityToListAssignee(entities);
        List<Assignee> assignees1 = new ArrayList<>();

        for (Assignee assignee:assignees){
            if (assignee.getName().equals(name) && assignee.getLastName().equals(lastName)){
                assignee1.setId(assignee.getId());
                assignee1.setName(assignee.getName());
                assignee1.setLastName(assignee.getLastName());
                assignee1.setPost(assignee.getPost());


                assignees1.add(assignee1);
            }
        }

        return assignees1;
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

    public static String marshal(AssigneeImpl assignee) throws JAXBException {
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(AssigneeImpl.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
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

    @Override
    public String getPost() {
        return post;
    }

    @Override
    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }



}