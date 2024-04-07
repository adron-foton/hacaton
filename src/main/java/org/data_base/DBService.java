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

    public static Connection getPostgresConnection(String user_login, String user_password) {
        try {
            DriverManager.registerDriver((java.sql.Driver) Class.forName("org.postgresql.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:postgresql://localhost:5432/.../user=").
                    append(user_login).
                    append("/password=").
                    append(user_password);

            Connection connection = DriverManager.getConnection(url.toString());

            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getPostgresConnection() {
        //TODO: уточнитб связь с базой
        try {
            String url = "jdbc:postgres:";
            String name = "tully";
            String password = "tully";

//            Jdbc3SimpleDataSource dataSource = new Jdbc3SimpleDataSource();
//            dataSource.setURL(url);
//            dataSource.setUser(name);
//            dataSource.setPassword(password);

            Connection connection = DriverManager.getConnection(url, name, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
