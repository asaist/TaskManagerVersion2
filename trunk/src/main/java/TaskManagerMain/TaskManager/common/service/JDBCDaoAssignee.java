package TaskManagerMain.TaskManager.common.service;

import TaskManagerMain.TaskManager.common.entity.Assignee;
import TaskManagerMain.TaskManager.common.entity.AssigneeImpl;
import TaskManagerMain.TaskManager.common.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDaoAssignee extends JDBCDao implements GenericDao {
    @Override
    public Integer create(Entity newInstance) {
        Connection connection = super.connectionToDatabase();
        Assignee assignee = (Assignee) newInstance;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ASSIGNEE(ID, NAME, LASTNAME, POST) VALUES (ASSIGNEE_SEQUENCE.nextval, '"+assignee.getName()+"', '"+assignee.getLastName()+"', '"+assignee.getPost()+"')");
            statement.executeUpdate();
            System.out.println("Запись " + assignee.toString() + " добавлена в базу данных");
            ResultSet resultSet = statement.executeQuery("select ID from Assignee");

            while (resultSet.next()){
               assignee.setId(resultSet.getInt("ID"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {

        }
        return assignee.getId();
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
                    assignee.setLastName(resultSet.getString("LASTNAME"));
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
            PreparedStatement statement = connection.prepareStatement("UPDATE ASSIGNEE SET NAME = '"+assignee.getName()+"', LASTNAME = '"+assignee.getLastName()+"', POST = '"+assignee.getPost()+"' WHERE ID = '"+assignee.getId()+"'");
            statement.executeUpdate();
            System.out.println("Запись " +  transientObject.toString() + " изменена ");
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
            System.out.println("Запись " +  persistentObject.toString() + " удалена ");
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
                assignee.setLastName(resultSet.getString("LASTNAME"));
                assignee.setPost(resultSet.getString("POST"));
                entitys.add((Entity) assignee);
                System.out.println("Запись " + assignee.toString() + " загружена");
            }
            System.out.println("Список Assignee загружен");
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