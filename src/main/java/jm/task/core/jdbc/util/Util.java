package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "newproject";
        String userName = "root";
        String password = "root";

        return getMyConnection (hostName, dbName, userName, password);
    }

    private static Connection getMyConnection(String hostName, String dbName, String userName, String password) throws SQLException,
            ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        return DriverManager.getConnection(connectionURL, userName,
                password);
    }
}
