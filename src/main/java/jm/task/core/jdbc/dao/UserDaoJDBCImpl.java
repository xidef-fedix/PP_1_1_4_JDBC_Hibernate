package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createBase() {
        try (Connection connection = getConnect();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE DATABASE IF NOT EXISTS users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() {
        try (Connection connection = getConnect();
             Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR(255)," +
                    " lastName VARCHAR(255)," +
                    " age TINYINT);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnect();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = getConnect();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO users (name, lastname, age) VALUES ( ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = getConnect();
             PreparedStatement statement = connection
                     .prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = getConnect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4));
                usersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try (Connection connection = getConnect();
             Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
