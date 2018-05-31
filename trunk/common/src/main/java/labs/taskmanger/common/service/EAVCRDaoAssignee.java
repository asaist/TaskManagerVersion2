package labs.taskmanger.common.service;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;
import labs.taskmanger.common.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EAVCRDaoAssignee extends JDBCDao<Assignee>  {

    final static Integer NAME_ID = 1;
    final static Integer LAST_NAME_ID = 2;
    final static Integer POST_ID = 3;

    final static Integer ASSIGNEE_ID = 1;

    public Integer create(Assignee newInstance) {
        Connection connection = super.connectionToDatabase();
        Assignee assignee = (Assignee) newInstance;


        try {
            PreparedStatement statementObject = connection.prepareStatement("INSERT INTO OBJECT(OBJECT_ID, OBJECT_TYPE_ID, NAME) VALUES (OBJECT_SEQUENCE.nextval, '" + ASSIGNEE_ID + "' , '" + assignee.toString() + "')");
            statementObject.executeUpdate();


            ResultSet resultSet = statementObject.executeQuery("SELECT OBJECT_ID FROM OBJECT");

            while (resultSet.next()) {
                assignee.setId(resultSet.getInt("OBJECT_ID"));
            }
            statementObject.close();
            resultSet.close();

            Statement statementName = connection.createStatement();
            statementName.executeUpdate("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('" + assignee.getId() + "', '" + NAME_ID + "', '" + assignee.getName() + "')");
            statementName.close();
            Statement statementLastName = connection.createStatement();
            statementLastName.executeUpdate("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('" + assignee.getId() + "', '" + LAST_NAME_ID + "', '" + assignee.getLastName() + "')");
            statementLastName.close();
            Statement statementPost = connection.createStatement();
            statementPost.executeUpdate("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('" + assignee.getId() + "', '" + POST_ID + "', '" + assignee.getPost() + "')");
            statementPost.close();

            System.out.println("Запись " + assignee.toString() + " добавлена в базу данных");


            connection.close();
        } catch (SQLException e) {

            System.err.println(e);

        }

        return assignee.getId();
        }

    public void update(Assignee transientObject) {
        Connection connection = super.connectionToDatabase();
        Assignee assignee = (AssigneeImpl) transientObject;
        try {
            PreparedStatement statementObject = connection.prepareStatement("UPDATE OBJECT SET NAME = '"+assignee.toString()+"' WHERE OBJECT_ID = '"+assignee.getId()+"'");
            statementObject.executeUpdate();
            statementObject.close();

            Statement statementName = connection.createStatement();
            statementName.executeUpdate("UPDATE PARAMS SET VALUE = '" + assignee.getName() + "' WHERE OBJECT_ID = '"+assignee.getId()+"' AND ATTRIBUTE_ID = '" + NAME_ID + "'");
            statementName.close();
            Statement statementLastName = connection.createStatement();
            statementLastName.executeUpdate("UPDATE PARAMS SET VALUE = '" + assignee.getLastName() + "' WHERE OBJECT_ID = '"+assignee.getId()+"' AND ATTRIBUTE_ID = '" + LAST_NAME_ID + "'");
            statementLastName.close();
            Statement statementPost = connection.createStatement();
            statementPost.executeUpdate("UPDATE PARAMS SET VALUE = '" + assignee.getPost() + "' WHERE OBJECT_ID = '"+assignee.getId()+"' AND ATTRIBUTE_ID = '" + POST_ID + "'");
            statementPost.close();

            System.out.println("Запись " +  assignee.toString() + " изменена ");
            connection.close();
        } catch (SQLException e) {
            System.err.println("Обновление не удалось " + e);
        }

    }


    public Entity read(Integer id) {

        Connection connection = super.connectionToDatabase();
        Assignee assignee = new AssigneeImpl();
        try {

            Statement statementValue = connection.createStatement();
            assignee.setId(id);
            ResultSet resultSetValue = statementValue.executeQuery("SELECT ATTRIBUTE_ID, VALUE FROM PARAMS WHERE OBJECT_ID = '" + assignee.getId() + "'");
            while (resultSetValue.next()) {
                if (NAME_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                    assignee.setName(resultSetValue.getString("VALUE"));
                }
                if (LAST_NAME_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                    assignee.setLastName(resultSetValue.getString("VALUE"));
                }
                if (POST_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                    assignee.setPost(resultSetValue.getString("VALUE"));
                }
            }

            System.out.println("Запись " + assignee.toString() + " загружена");
            resultSetValue.close();
            statementValue.close();
            connection.close();
        }
        catch (SQLException e) {
            System.err.println("Невозможно произвести чтение " + e);
        }
        catch (NullPointerException e) {
            System.err.println("Невозможно произвести чтение " + e);
        }

        return (Entity) assignee;

    }

    public void delete(Assignee persistentObject) {

        Assignee assignee = (AssigneeImpl) persistentObject;

        Connection connection = super.connectionToDatabase();
        try {
            PreparedStatement statementObject = connection.prepareStatement("DELETE FROM OBJECT WHERE OBJECT_ID = '"+assignee.getId()+"'");
            statementObject.executeUpdate();
            statementObject.close();

            Statement statementName = connection.createStatement();
            statementName.executeUpdate("DELETE FROM PARAMS WHERE OBJECT_ID = '"+assignee.getId()+"'");
            statementName.close();

            System.out.println("Запись " +  persistentObject.toString() + " удалена ");

            connection.close();
        } catch (SQLException e) {
            System.err.println("Удалить не удалось" + e);

        }

    }


    public List<Entity> readAll() {

        List<Entity> entitys = new ArrayList();
        Connection connection = super.connectionToDatabase();

        try {
            Statement statementId = connection.createStatement();
            ResultSet resultSetId = statementId.executeQuery("SELECT OBJECT_ID FROM OBJECT WHERE OBJECT_TYPE_ID = '" + ASSIGNEE_ID + "' ");
            while (resultSetId.next()) {
                Assignee assignee = new AssigneeImpl();
                Statement statementValue = connection.createStatement();
                assignee.setId(resultSetId.getInt("OBJECT_ID"));
                ResultSet resultSetValue = statementValue.executeQuery("SELECT ATTRIBUTE_ID, VALUE FROM PARAMS WHERE OBJECT_ID = '" + assignee.getId() + "'");
                while (resultSetValue.next()) {

                    if (NAME_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))){
                        assignee.setName(resultSetValue.getString("VALUE"));
                    }

                    if (LAST_NAME_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))){
                        assignee.setLastName(resultSetValue.getString("VALUE"));
                    }

                    if (POST_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))){
                        assignee.setPost(resultSetValue.getString("VALUE"));
                    }

                }
                entitys.add((Entity) assignee);
                System.out.println("Запись " + assignee.toString() + " загружена");
                resultSetValue.close();
                statementValue.close();
            }
            System.out.println("Список Assignee загружен");

            resultSetId.close();

            statementId.close();
            connection.close();
        }
        catch (SQLException e) {
            System.err.println("Невозможно произвести чтение " + e);
        }
        catch (NullPointerException e) {
            System.err.println("Невозможно произвести чтение " + e);
        }

        return entitys;
    }

    public void checkFile() {

    }




    }
