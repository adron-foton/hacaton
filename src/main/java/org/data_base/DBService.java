package org.data_base;

import org.data_base.dao.Profiles_TableEnvironment;
import org.data_base.dataSets.UsersDataSet;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBService {

    private static final DataSource dataSource = getPostgresDataSource();
    private Connection connection;

    public DBService() throws SQLException {
        connection = dataSource.getConnection();
    }

    public UsersDataSet getUser(long id) throws DBException {
        try {
            Connection connection = dataSource.getConnection();
            return new Profiles_TableEnvironment(connection).getByID(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void addUser(String login, String first_name, String password) throws DBException {
        try {
            connection.setAutoCommit(false);
            Profiles_TableEnvironment dao = new Profiles_TableEnvironment(connection);
            dao.insertUser(login, password, first_name);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {

            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignoring) {}
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

            String path = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=01229";
//            String user = "postgres";
//            String password = "01229";

            Connection connection = DriverManager.getConnection(path);

            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getPostgresDataSource() {
        try {
            DriverManager.registerDriver((java.sql.Driver) Class.forName("org.postgresql.Driver").newInstance());

            String path = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=01229";
//            String user = "postgres";
//            String password = "01229";

            PGSimpleDataSource pgDataSource = new PGSimpleDataSource();
            pgDataSource.setUrl(path);

            return pgDataSource;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
