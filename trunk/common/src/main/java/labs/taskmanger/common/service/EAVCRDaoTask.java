package labs.taskmanger.common.service;

import labs.taskmanger.common.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EAVCRDaoTask extends JDBCDao<Task> {

    final static Integer TASK_NAME_ID = 4;
    final static Integer DESCRIPTION_ID = 5;
    final static Integer DEADLINE_ID = 6;
    final static Integer PRIORITY_ID = 7;
    final static Integer STATUS_ID = 8;

    final static Integer TASK_ID = 2;


    public Integer create(Task newInstance) {

        Connection connection = super.connectionToDatabase();
        Task task = (Task) newInstance;


        try {
            PreparedStatement statementObject = connection.prepareStatement("INSERT INTO OBJECT(OBJECT_ID, OBJECT_TYPE_ID, NAME) VALUES (OBJECT_SEQUENCE.nextval, '" + TASK_ID + "' , '" + task.toString() + "')");
            statementObject.executeUpdate();


            ResultSet resultSet = statementObject.executeQuery("SELECT OBJECT_ID FROM OBJECT");

            while (resultSet.next()) {
                task.setId(resultSet.getInt("OBJECT_ID"));
            }
            statementObject.close();
            resultSet.close();

            Statement statementTaskName = connection.createStatement();
            statementTaskName.executeUpdate("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('" + task.getId() + "', '" + TASK_NAME_ID + "', '" + task.getTaskName() + "')");
            statementTaskName.close();
            Statement statementDescription = connection.createStatement();
            statementDescription.executeUpdate("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('" + task.getId() + "', '" + DESCRIPTION_ID + "', '" + task.getDescription() + "')");
            statementDescription.close();
            Statement statementDeadline = connection.createStatement();
            statementDeadline.executeUpdate("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('" + task.getId() + "', '" + DEADLINE_ID + "', '" + task.getDeadline() + "')");
            statementDeadline.close();

            Statement statementPriority = connection.createStatement();
            statementPriority.executeUpdate("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('" + task.getId() + "', '" + PRIORITY_ID + "', '" + task.getPriority() + "')");
            statementPriority.close();
            Statement statementStatus = connection.createStatement();
            statementStatus.executeUpdate("INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('" + task.getId() + "', '" + STATUS_ID + "', '" + task.getStatus() + "')");
            statementStatus.close();



            System.out.println("Запись " + task.toString() + " добавлена в базу данных");


            connection.close();
        } catch (SQLException e) {

            System.err.println(e);

        }

        return task.getId();
    }

    public Entity read(Integer id) {

        Connection connection = super.connectionToDatabase();
        Task task = new TaskImpl();
        try {

            Statement statementValue = connection.createStatement();
            task.setId(id);
            ResultSet resultSetValue = statementValue.executeQuery("SELECT ATTRIBUTE_ID, VALUE FROM PARAMS WHERE OBJECT_ID = '" + task.getId() + "'");
            while (resultSetValue.next()) {
                if (TASK_NAME_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                    task.setTaskName(resultSetValue.getString("VALUE"));
                }
                if (DESCRIPTION_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                    task.setDescription(resultSetValue.getString("VALUE"));
                }
                if (DEADLINE_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                    task.setDeadline(resultSetValue.getString("VALUE"));
                }
                if (PRIORITY_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                    task.setPriority(resultSetValue.getString("VALUE"));
                }
                if (STATUS_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                    task.setStatus(resultSetValue.getString("VALUE"));
                }
            }

            System.out.println("Запись " + task.toString() + " загружена");
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

        return (Entity) task;

    }

    public void update(Task transientObject) {

    }

    public void delete(Task persistentObject) {

        Task task = (TaskImpl) persistentObject;

        Connection connection = super.connectionToDatabase();
        try {
            PreparedStatement statementObject = connection.prepareStatement("DELETE FROM OBJECT WHERE OBJECT_ID = '"+task.getId()+"'");
            statementObject.executeUpdate();
            statementObject.close();

            Statement statementName = connection.createStatement();
            statementName.executeUpdate("DELETE FROM PARAMS WHERE OBJECT_ID = '"+task.getId()+"'");
            statementName.close();

            System.out.println("Запись " +  task.toString() + " удалена ");

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
                ResultSet resultSetId = statementId.executeQuery("SELECT OBJECT_ID FROM OBJECT WHERE OBJECT_TYPE_ID = '" + TASK_ID + "' ");
                while (resultSetId.next()) {
                    Task task = new TaskImpl();
                    Statement statementValue = connection.createStatement();
                    task.setId(resultSetId.getInt("OBJECT_ID"));
                    ResultSet resultSetValue = statementValue.executeQuery("SELECT ATTRIBUTE_ID, VALUE FROM PARAMS WHERE OBJECT_ID = '" + task.getId() + "'");
                    while (resultSetValue.next()) {

                        if (TASK_NAME_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                            task.setTaskName(resultSetValue.getString("VALUE"));
                        }
                        if (DESCRIPTION_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                            task.setDescription(resultSetValue.getString("VALUE"));
                        }
                        if (DEADLINE_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                            task.setDeadline(resultSetValue.getString("VALUE"));
                        }
                        if (PRIORITY_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                            task.setPriority(resultSetValue.getString("VALUE"));
                        }
                        if (STATUS_ID.equals(resultSetValue.getInt("ATTRIBUTE_ID"))) {
                            task.setStatus(resultSetValue.getString("VALUE"));
                        }

                    }
                    entitys.add((Entity) task);
                    System.out.println("Запись " + task.toString() + " загружена");
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