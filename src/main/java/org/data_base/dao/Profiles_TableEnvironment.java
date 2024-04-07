package org.data_base.dao;

import org.data_base.dataSets.UsersDataSet;
import org.data_base.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class Profiles_TableEnvironment {

    private Executor executor;

    public Profiles_TableEnvironment(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet getByID(long id) throws SQLException {
        return executor.execQuery("select * from profiles where id=" + id, result -> {
            result.next();
            return new UsersDataSet(
                    result.getLong(1),
                    result.getString("first_name"),
                    result.getString("login"),
                    result.getString("password")
            );
        });//TODO:
    }

    public long getUserId(String login) throws SQLException {
        return executor.execQuery("select * from profiles where login='" + login + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(String login, String password, String first_name) throws SQLException {
        executor.execUpdate("insert into profiles (login, password, first_name) values ('" + login + "', '" + password + "', '" + first_name + "')");
    }

//    public void createTable() throws SQLException {
//        executor.execUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), primary key (id))");
//    }

//    public void dropTable() throws SQLException {
//        executor.execUpdate("drop table users");
//    }
}
