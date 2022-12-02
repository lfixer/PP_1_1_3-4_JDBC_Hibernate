package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/users_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1754603!11";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch (Exception e) {
            System.out.println("Не удалось создать подключение к БД");
        }
        return connection;
    }



}
