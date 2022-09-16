package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl extends UserDaoJDBCImpl implements UserService {

    UserDao udjd = new UserDaoJDBCImpl();

    public void createUsersTable() {

        udjd.createUsersTable();
    }

    public void dropUsersTable() {
        udjd.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {

    udjd.saveUser(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {

    udjd.removeUserById(id);
    }

    public List<User> getAllUsers() {

      return udjd.getAllUsers();

    }

    public void cleanUsersTable() {
        udjd.cleanUsersTable();

    }
}
