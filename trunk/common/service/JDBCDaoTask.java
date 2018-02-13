package common.service;

import common.entity.Assignee;
import common.entity.AssigneeImpl;
import common.entity.Entity;
import common.entity.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDaoTask extends JDBCDao implements GenericDao {
    @Override
    public Integer create(Entity newInstance) {
        Connection connection = super.connectionToDatabase();
        Task task = (Task) newInstance;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO TASK(ID, NAME, DESCRIPTION, DEADLINE, PRIORITY, STATUS) VALUES (TASK_SEQUENCE.nextval, '"+task.getTaskName()+"', '"+task.getDescription()+"')");
            statement.executeUpdate();
            System.out.println("Запись " + task.toString() + " добавлена в базу данных");
            ResultSet resultSet = statement.executeQuery("select ID from Assignee");

            while (resultSet.next()){
                task.setId(resultSet.getInt("ID"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {

        }
        return task.getId();
    }

    @Override
    public Entity read(Integer id) {
        Assignee assignee = new AssigneeImpl();
        Connection connection = super.connectionToDatabase();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, name, lastname, post from Assignee");
            while (resultSet.next()){
                if (id.equals(resultSet.getInt("ID"))) {
                    assignee.setId(resultSet.getInt("ID"));
                    assignee.setName(resultSet.getString("NAME"));
                    assignee.setLastname(resultSet.getString("LASTNAME"));
                    assignee.setPost(resultSet.getString("POST"));
                    System.out.println("Запись загружена "+ assignee.toString());
                }
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Невозможно произвести чтение " + e);
        }

        return (Entity) assignee;
    }

    @Override
    public void update(Entity transientObject) {
        Connection connection = super.connectionToDatabase();
        Assignee assignee = (AssigneeImpl) transientObject;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ASSIGNEE SET NAME = '"+assignee.getName()+"', LASTNAME = '"+assignee.getLastname()+"', POST = '"+assignee.getPost()+"' WHERE ID = '"+assignee.getId()+"'");
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Обновление не удалось " + e);
        }


    }

    @Override
    public void delete(Entity persistentObject) {
        Connection connection = super.connectionToDatabase();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ASSIGNEE WHERE ID = '"+persistentObject.getId()+"'");
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Удалить не удалось" + e);

        }


    }

    @Override
    public List<Entity> readAll() {

        List<Entity> entitys = new ArrayList();
        Connection connection = super.connectionToDatabase();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, name, lastname, post from Assignee");
            while (resultSet.next()) {
                Assignee assignee = new AssigneeImpl();
                assignee.setId(resultSet.getInt("ID"));
                assignee.setName(resultSet.getString("NAME"));
                assignee.setLastname(resultSet.getString("LASTNAME"));
                assignee.setPost(resultSet.getString("POST"));
                entitys.add((Entity) assignee);
                System.out.println("Запись " + assignee.toString() + " загружена");
            }
            statement.close();
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

    @Override
    public void checkFile() {
        //elfkbnm
    }
}