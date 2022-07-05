package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users(" +
                "ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR(100), " +
                "LASTNAME VARCHAR(100), AGE INT, PRIMARY KEY (ID) )";

        try (Connection connection = Util.getMyConnection();
             Statement stat = connection.createStatement()) {

            stat.executeUpdate(sql);
            System.out.println("Table was created!");

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMyConnection();
             Statement stat = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS users";

            stat.executeUpdate(sql);
            System.out.println("Table was dropped");

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES(?, ?, ?)";

        try (Connection connection = Util.getMyConnection();
             PreparedStatement preStat = connection.prepareStatement(sql)) {

            preStat.setString(1, name);
            preStat.setString(2, lastName);
            preStat.setByte(3, age);

            preStat.executeUpdate();
            System.out.println("User was added!");

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?";

        try (Connection connection = Util.getMyConnection();
             PreparedStatement preStat = connection.prepareStatement(sql)) {

            preStat.setLong(1, id);

            preStat.executeUpdate();
            System.out.println("User was removed!");

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT id, name, lastName, age FROM users";

        try (Connection connection = Util.getMyConnection();
             Statement stat = connection.createStatement()) {

            ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
            System.out.println("List of users is ready!");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (Connection connection = Util.getMyConnection();
             Statement stat = connection.createStatement()) {

            stat.executeUpdate(sql);
            System.out.println("Table was cleaned!");

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    }
