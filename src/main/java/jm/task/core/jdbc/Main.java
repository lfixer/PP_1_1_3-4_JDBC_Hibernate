package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;


public class Main {
    private final static UserService service = new UserServiceImpl();

    public static void main(String[] args) {
        service.createUsersTable();

        service.saveUser("A", "A", (byte) 1);
        service.saveUser("B", "B", (byte) 2);
        service.saveUser("C", "C", (byte) 3);
        service.saveUser("D", "D", (byte) 4);

        service.removeUserById(2);
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();

    }
}
