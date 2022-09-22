package jm.task.core.jdbc;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       UserService userService = new UserServiceImpl();
       userService.createUsersTable();
       userService.saveUser("Pavel", "Galkin", (byte) 32);
       userService.saveUser("Alexandr", "Muzalevskiy", (byte) 27);
       userService.saveUser("Nikita", "Grishin", (byte) 26);
       userService.saveUser("Anna", "Fedko", (byte) 22);
       List<User> userList =  userService.getAllUsers();
            for (int i = 0; i < userList.size(); i++) {
                System.out.println("User с именем " + userList.get(i).getName() + " добавлен в базу данных");
            }
       System.out.println(userService.getAllUsers());
       userService.cleanUsersTable();
       userService.dropUsersTable();









    }
}
