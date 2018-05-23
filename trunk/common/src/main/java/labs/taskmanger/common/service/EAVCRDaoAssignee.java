package labs.taskmanger.common.service;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;
import labs.taskmanger.common.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EAVCRDaoAssignee extends JDBCDao implements GenericDao  {


    public Integer create(Assignee newInstance) {
            Connection connection = super.connectionToDatabase();
            Assignee assignee = (Assignee) newInstance;
        
            try {
                PreparedStatement statementObject = connection.prepareStatement("INSERT INTO OBJECT(OBJECT_ID, OBJECT_TYPE_ID, NAME) VALUES (OBJECT_SEQUENCE.nextval, SELECT OBJECT_TYPE_ID FROM OBJECT_TYPE WHERE NAME='ASSIGNEE', '" + assignee.toString() + "')");
                statementObject.executeUpdate();
                statementObject.close();
                PreparedStatement statementName = connection.prepareStatement("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES (SELECT OBJECT_ID FROM OBJECT WHERE NAME='" + assignee.toString() + "', SELECT ATTRIBUTE_ID FROM ATTRIBUTES WHERE NAME='NAME', '" + assignee.getName() + "')");
                statementName.executeUpdate();
                ResultSet resultSet = statementObject.executeQuery("SELECT OBJECT_ID FROM OBJECT ");

                while (resultSet.next()) {
                    assignee.setId(resultSet.getInt("OBJECT_ID"));
                }
                resultSet.close();
                statementName.close();

                PreparedStatement statementLastName = connection.prepareStatement("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES (SELECT OBJECT_ID FROM OBJECT WHERE NAME='" + assignee.toString() + "', SELECT ATTRIBUTE_ID FROM ATTRIBUTES WHERE NAME='LAST_NAME', '" + assignee.getLastName() + "')");
                statementLastName.executeUpdate();
                statementLastName.close();

                PreparedStatement statementPost = connection.prepareStatement("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES (SELECT OBJECT_ID FROM OBJECT WHERE NAME='" + assignee.toString() + "', SELECT ATTRIBUTE_ID FROM ATTRIBUTES WHERE NAME='POST', '" + assignee.getPost() + "')");
                statementPost.executeUpdate();
                statementPost.close();

                connection.close();
                System.out.println("Запись " + assignee.toString() + " добавлена в базу данных");
            } catch (SQLException e) {

            }


            return assignee.getId();
        }

    public Entity read(Integer id) {

        return null;

    }

    public void update(Entity transientObject) {

    }

    public void delete(Entity persistentObject) {

    }

    public List<Entity> readAll() {

        return null;

    }

    public void checkFile() {

    }
}