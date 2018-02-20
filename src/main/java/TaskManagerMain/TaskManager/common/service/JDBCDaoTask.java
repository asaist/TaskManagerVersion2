package TaskManagerMain.TaskManager.common.service;

import TaskManagerMain.TaskManager.common.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDaoTask extends JDBCDao implements GenericDao {
    @Override
    public Integer create(Entity newInstance) {
        Connection connection = super.connectionToDatabase();
        Task task = (Task) newInstance;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO TASK(ID, NAME, DESCRIPTION, DEADLINE, PRIORITY, STATUS) VALUES (TASK_SEQUENCE.nextval, '"+task.getTaskName()+"',  '"+task.getDescription()+"', '"+task.getDeadline()+"', '"+task.getPriority()+"', '"+task.getStatus()+"')");
            statement.executeUpdate();
            System.out.println("Запись " + task.toString() + " добавлена в базу данных");
            ResultSet resultSet = statement.executeQuery("select ID from TASK");

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
        Task task = new TaskImpl();
        Connection connection = super.connectionToDatabase();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, name, description, deadline, priority, status from Task");
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

    @Override
    public void update(Entity transientObject) {
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

    @Override
    public void delete(Entity persistentObject) {
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

    @Override
    public List<Entity> readAll() {

        List<Entity> entitys = new ArrayList();
        Connection connection = super.connectionToDatabase();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, name, description, deadline, priority, status from Task");
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

    @Override
    public void checkFile() {

    }
}