package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Util ut = new Util();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            ut.useBase();
            ut.getConnect()
                    .createStatement()
                    .execute("CREATE TABLE users (id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                            " name VARCHAR(255)," +
                            " lastName VARCHAR(255)," +
                            " age TINYINT);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        ut.useBase();
        try {
            ut.getConnect().createStatement().execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        ut.useBase();
        String sql = "INSERT INTO users (name, lastname, age) VALUES ( ?, ?, ?)";
        try {
            PreparedStatement statement = ut.getConnect().prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        ut.useBase();
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement statement = ut.getConnect().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        ut.useBase();
        List <User> usersList = new ArrayList<>();
        try {
            Statement statement = ut.getConnect().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getLong(1)
                        , resultSet.getString(2)
                        , resultSet.getString(3)
                        , resultSet.getByte(4));
                usersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;

    }

    public void cleanUsersTable() {
        ut.useBase();
        String sql = "TRUNCATE TABLE users";
        Statement statement = null;
        try {
            statement = ut.getConnect().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
