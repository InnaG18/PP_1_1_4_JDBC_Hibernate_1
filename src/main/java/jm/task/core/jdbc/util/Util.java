package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static SessionFactory sessionFactory;
    private static final String HOST_NAME = "localhost";
    private static final String DB_NAME = "newproject";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getMyConnection() throws SQLException{
        try {
            Class.forName(DRIVER);
            String connectionURL = "jdbc:mysql://" + HOST_NAME + ":3306/" + DB_NAME;

            return DriverManager.getConnection(connectionURL, USER_NAME,
                    PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, DRIVER);
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/newproject");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "root");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
