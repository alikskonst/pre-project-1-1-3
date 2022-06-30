package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

public class Util {

    public Optional<Connection> getConnection() {
        try {
            Class.forName("");
            return Optional.of(
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/pre_project_113?useSSL=false&useUnicode=true&characterEncoding=utf-8&characterSetResults=utf-8&autoReconnect=true",
                            "root",
                            "root"
                    )
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
