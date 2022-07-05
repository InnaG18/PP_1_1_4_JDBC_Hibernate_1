package jm.task.core.jdbc.service;

import com.mysql.cj.Query;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl  extends UserDaoJDBCImpl implements UserService {
    private  final UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {
        super.createUsersTable();
    }

    public void dropUsersTable() {
        super.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        super.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        super.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return super.getAllUsers();
    }

    public void cleanUsersTable() {
        super.cleanUsersTable();
    }
}
