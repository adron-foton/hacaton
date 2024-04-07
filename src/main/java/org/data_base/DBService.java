package org.data_base;

import org.data_base.dao.UsersDAO;
import org.data_base.dataSets.UsersDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getPostgresConnection();
    }//TODO:

    public UsersDataSet getUser(long id) throws DBException {
        try {
            return new UsersDAO(connection).get(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public long addUser(String name) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.insertUser(name);
            connection.commit();
            return dao.getUserId(name);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

//    public void printConnectInfo() {
//        try {
//            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
//            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
//            System.out.println("Driver: " + connection.getMetaData().getDriverName());
//            System.out.println("Autocommit: " + connection.getAutoCommit());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getPostgresConnection() {
        try {
            DriverManager.registerDriver((java.sql.Driver) Class.forName("org.postgresql.Driver").newInstance());

            String path = "jdbc:postgresql://localhost:5432/javaDB";
            String user = "postgres";
            String password = "01229";

            Connection connection = DriverManager.getConnection(path, user, password);

            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
