package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String USERNAME = "root";
    private final String PASSWORD = "89888988";
    private final String URL = "jdbc:mysql://localhost:3306";

    public Connection getConnect() {
        return connect;
    }

    private Connection connect;

    public void connectToBase() {
        try {
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createBase() {
        try {
            var createBase = connect
                    .createStatement()
                    .execute("CREATE DATABASE IF NOT EXISTS users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void useBase () {
        try {
            connectToBase();
            var useBase = connect.createStatement().execute("USE users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

