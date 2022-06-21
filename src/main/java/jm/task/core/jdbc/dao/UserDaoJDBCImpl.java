package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.connection.ConnectionSingleton;
import jm.task.core.jdbc.connection.QuerySingleton;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Optional<Connection> connection = ConnectionSingleton.instance(null).getConnection();
        if (connection.isPresent()) {
            String query = QuerySingleton.instance(null).getQuery("userCreateTable");
            try (Statement statement = connection.get().createStatement()) {
                // todo: сообщить?
                statement.execute(query);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Optional<Connection> connection = ConnectionSingleton.instance(null).getConnection();
        if (connection.isPresent()) {
            String query = QuerySingleton.instance(null).getQuery("userDropTable");
            try (Statement statement = connection.get().createStatement()) {
                // todo: сообщить?
                statement.execute(query);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Optional<Connection> connection = ConnectionSingleton.instance(null).getConnection();
        if (connection.isPresent()) {
            String query = QuerySingleton.instance(null).getQuery("userCreate");
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                if (preparedStatement.executeUpdate() > 0) {
                    System.out.println("User was added");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Optional<Connection> connection = ConnectionSingleton.instance(null).getConnection();
        if (connection.isPresent()) {
            String query = QuerySingleton.instance(null).getQuery("userRemove");
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                if (preparedStatement.executeUpdate() > 0) {
                    System.out.println("User was removed");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        Optional<Connection> connection = ConnectionSingleton.instance(null).getConnection();
        if (connection.isPresent()) {
            String query = QuerySingleton.instance(null).getQuery("userFindAll");
            try (Statement statement = connection.get().createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                // todo: как память выделить?
                List<User> userList = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setAge(resultSet.getByte("age"));
                    userList.add(user);
                }
                return userList;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    public void cleanUsersTable() {
        Optional<Connection> connection = ConnectionSingleton.instance(null).getConnection();
        if (connection.isPresent()) {
            // todo: пересоздать или очистить?
            String query = QuerySingleton.instance(null).getQuery("userRemoveAll");
//            String query = QuerySingleton.instance().getQuery("userTruncateTable");
            try (Statement statement = connection.get().createStatement()) {
                statement.execute(query);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
