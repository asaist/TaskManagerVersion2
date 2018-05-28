package labs.taskmanger.common.service;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;
import labs.taskmanger.common.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EAVCRDaoAssignee extends JDBCDao<Assignee> {

    final static Integer NAME_ID = 1;
    final static Integer LAST_NAME_ID = 2;
    final static Integer POST_ID = 3;

    final static Integer ASSIGNEE_ID = 1;

    public Integer create(Assignee newInstance) {

        return null;
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

    public void update(Assignee transientObject) {

    }

    public void delete(Assignee persistentObject) {

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
