package jm.task.core.jdbc.connection;

import java.sql.Connection;
import java.util.Optional;

public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    private final Optional<Connection> connection;

    private ConnectionSingleton(Optional<Connection> connection) {
        this.connection = connection;
    }

    public static ConnectionSingleton instance(Connection connection) {
        if (instance == null) {
            instance = new ConnectionSingleton(Optional.empty());
        }
        if (connection != null) {
            instance = new ConnectionSingleton(Optional.of(connection));
        }
        return instance;
    }

    public Optional<Connection> getConnection() {
        return connection;
    }
}
