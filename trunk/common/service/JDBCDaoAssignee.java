package common.service;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.xml.internal.bind.v2.model.core.ID;
import common.entity.Assignee;
import common.entity.AssigneeImpl;
import common.entity.Entity;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDaoAssignee extends JDBCDao implements GenericDao {
    @Override
    public Integer create(Entity newInstance) {
        Connection connection = super.connectionToDatabase();
        Assignee assignee = (Assignee) newInstance;
        String name = assignee.getName();
        String lastName = assignee.getLastname();
        String post = assignee.getPost();
        Integer id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ASSIGNEE(ID, NAME, LASTNAME, POST) VALUES (ASSIGNEE_SEQUENCE.nextval, '"+name+"', '"+lastName+"', '"+post+"')");
            System.out.println("Запись " + assignee.toString() + " добавлена в базу данных");
            ResultSet resultSet = statement.executeQuery("select id from Assignee");
            while (resultSet.next()){
               id = resultSet.getInt("ID");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {

        }
        return id;
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
            }
            statement.close();
            connection.close();
        }
            catch (SQLException e) {
            System.err.println("Невозможно произвести чтение " + e);
        }



        return entitys;
    }

    @Override
    public void checkFile() {
        //elfkbnm
    }



}