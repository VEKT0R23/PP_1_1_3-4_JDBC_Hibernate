package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDaoJDBCImpl userDAO = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        userDAO.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDAO.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDAO.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDAO.cleanUsersTable();
    }
}
