package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Root";


    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


}


