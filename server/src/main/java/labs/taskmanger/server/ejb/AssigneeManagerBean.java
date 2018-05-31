package labs.taskmanger.server.ejb;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;
import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.service.EAVCRDaoAssignee;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
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
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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


    public List<Assignee> parseListEntityToListAssignee(List<Entity> entities) {
        Assignee assignee = new AssigneeImpl();
        List<Assignee> assignees = new ArrayList();
        for (Entity entity: entities){
            assignee = (Assignee) entity;
            assignees.add(assignee);

        }
        return assignees;
    }

    public void inicialize (GenericDao<Assignee> assigneeDao) {
        this.assigneeDAO = assigneeDao;
    }

    public void assigneeToXML (List<Assignee> assignees) throws ParserConfigurationException, TransformerException {

        File destFile = new File("C:\\Export\\");

        destFile.mkdirs();

        destFile = new File("C:\\Export\\ExportAssignee.xml");

            try {

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("ListAssignee");
                doc.appendChild(rootElement);

                Integer id = 1;

                for (Assignee assigneeFromList:assignees) {



                    Element assignee = doc.createElement("Assignee");
                    rootElement.appendChild(assignee);

                    Attr attr = doc.createAttribute("id");
                    attr.setValue(id.toString());
                    assignee.setAttributeNode(attr);

                    id++;


                    Element name = doc.createElement("Name");
                    name.appendChild(doc.createTextNode(assigneeFromList.getName()));
                    assignee.appendChild(name);

                    Element lastname = doc.createElement("Lastname");
                    lastname.appendChild(doc.createTextNode(assigneeFromList.getLastName()));
                    assignee.appendChild(lastname);

                    Element post = doc.createElement("Post");
                    post.appendChild(doc.createTextNode(assigneeFromList.getPost()));
                    assignee.appendChild(post);

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
        }



