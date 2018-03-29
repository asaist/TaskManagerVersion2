package labs.taskmanger.common.service;

import labs.taskmanger.common.entity.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public abstract class JDBCDao<TEntity extends Entity> implements GenericDao<TEntity> {



    public Integer create(TEntity newInstance) {
        Connection connection = connectionToDatabase();
        try {
            Statement statement = connection.createStatement();

            } catch (SQLException e) {

        }


        return null;
    }




    public Connection connectionToDatabase() {
        final String USER_NAME = "system";
        final String PASSWORD = "qwerty";
        final String URL = "jdbc:oracle:thin:@localhost:1521:TaskManager";

        Connection connection = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("The connection to the database was successful");
            }

        }
        catch (ClassNotFoundException e) {
            System.err.println("Problems with connecting to the database " + e);
        }
        catch (SQLException e) {
            System.err.println("Problems with connecting to the database " + e);
        }

        return connection;


    }
}