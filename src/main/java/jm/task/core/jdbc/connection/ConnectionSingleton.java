package jm.task.core.jdbc.connection;

import java.sql.Connection;
import java.util.Optional;

public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    private final Optional<Connection> connection;

    private ConnectionSingleton(Optional<Connection> connection) {
        this.connection = connection;
    }

    public static ConnectionSingleton instance() {
        if (instance == null) {
            instance = new ConnectionSingleton(Optional.empty());
        }
        return instance;
    }

    public Optional<Connection> getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        instance = new ConnectionSingleton(connection == null ? Optional.empty() : Optional.of(connection));
    }
}
