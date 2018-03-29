package labs.taskmanger.common.service;

import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.entity.Task;
import labs.taskmanger.common.entity.TaskImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDaoTask extends JDBCDao<Task> {

    public Integer create(Task newInstance) {
        Connection connection = super.connectionToDatabase();
        Task task =  newInstance;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO TASK(ID, NAME, DESCRIPTION, DEADLINE, PRIORITY, STATUS) VALUES (TASK_SEQUENCE.nextval, '"+task.getTaskName()+"',  '"+task.getDescription()+"', '"+task.getDeadline()+"', '"+task.getPriority()+"', '"+task.getStatus()+"')");
            statement.executeUpdate();
            System.out.println("Запись " + task.toString() + " добавлена в базу данных");
            ResultSet resultSet = statement.executeQuery("SELECT ID from TASK");

            while (resultSet.next()){
                task.setId(resultSet.getInt("ID"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {

        }
        return task.getId();
    }


    public Entity read(Integer id) {
        Task task = new TaskImpl();
        Connection connection = super.connectionToDatabase();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME, DESCRIPTION, DEADLINE, PRIORITY, STATUS from TASK");
            while (resultSet.next()){
                if (id.equals(resultSet.getInt("ID"))) {
                    task.setId(resultSet.getInt("ID"));
                    task.setTaskName(resultSet.getString("NAME"));
                    task.setDescription(resultSet.getString("DESCRIPTION"));
                    task.setDeadline(resultSet.getString("DEADLINE"));
                    task.setPriority(resultSet.getString("PRIORITY"));
                    task.setStatus(resultSet.getString("STATUS"));
                    System.out.println("Запись загружена "+ task.toString());
                }
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Невозможно произвести чтение " + e);
        }

        return (Entity) task;
    }


    public void update(Task transientObject) {
        Connection connection = super.connectionToDatabase();
        Task task = (TaskImpl) transientObject;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE TASK SET NAME = '"+task.getTaskName()+"', DESCRIPTION = '"+task.getDescription()+"', DEADLINE = '"+task.getDeadline()+"', PRIORITY = '"+task.getPriority()+"', STATUS = '"+task.getStatus()+"' WHERE ID = '"+task.getId()+"'");
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Обновление не удалось " + e);
        }


    }


    public void delete(Task persistentObject) {
        Connection connection = super.connectionToDatabase();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Task WHERE ID = '"+persistentObject.getId()+"'");
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Удалить не удалось" + e);

        }


    }


    public List<Entity> readAll() {

        List<Entity> entitys = new ArrayList();
        Connection connection = super.connectionToDatabase();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME, DESCRIPTION, DEADLINE, PRIORITY, STATUS from TASK");
            while (resultSet.next()) {

                Task task = new TaskImpl();
                task.setId(resultSet.getInt("ID"));
                task.setTaskName(resultSet.getString("NAME"));
                task.setDescription(resultSet.getString("DESCRIPTION"));
                task.setDeadline(resultSet.getString("DEADLINE"));
                task.setPriority(resultSet.getString("PRIORITY"));
                task.setStatus(resultSet.getString("STATUS"));
                entitys.add((Entity) task);
                System.out.println("Запись " + task.toString() + " загружена");
            }
            System.out.println("Список Task загружен ");
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


    public void checkFile() {

    }
}