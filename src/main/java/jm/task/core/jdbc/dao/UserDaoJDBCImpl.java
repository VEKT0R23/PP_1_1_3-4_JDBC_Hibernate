package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), lastName VARCHAR(100), age INT)")) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица создана");
            connection.commit();
        } catch (SQLException e) {
            try (Connection connection = Util.getConnection()) {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            System.out.println("Не удалось создать таблицу");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS users")) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица удалена ");
            connection.commit();
        } catch (SQLException e) {
            try (Connection connection = Util.getConnection()) {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            System.out.println("Таблицу удалить не удалось");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, lastName, age) VALUES(?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.commit();
        } catch (SQLException e) {
            try (Connection connection = Util.getConnection()) {
                connection.commit();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            System.out.println("Не удалось добавить в базу User с именем – " + name);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User удален");
            connection.commit();
        } catch (SQLException e) {
            try (Connection connection = Util.getConnection()) {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            System.out.println("Не удалось удалить User");
        }
    }

    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, lastName, age FROM users")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUser.add(user);
            }
            System.out.println("Все User-ы получены");
            connection.commit();
        } catch (Exception e) {
            try (Connection connection = Util.getConnection()) {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            System.out.println("Не удалось получить User-ов");
        }
        return allUser;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE users")) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица очищена");
            connection.commit();
        } catch (SQLException e) {
            try (Connection connection = Util.getConnection()) {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            System.out.println("Не удалось очистить");
        }
    }
}
