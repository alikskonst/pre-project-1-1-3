package jm.task.core.jdbc.util;

import jm.task.core.jdbc.conf.ConfProvider;
import jm.task.core.jdbc.connection.ConnectionSingleton;
import jm.task.core.jdbc.connection.QuerySingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

public class Util {

    public void init() {
        ConfProvider confProvider = new ConfProvider();
        ConnectionSingleton.instance(createConnection(confProvider.connection()));
        QuerySingleton.instance(confProvider.queries());
    }

    private Connection createConnection(Map<String, String> conf) {
        try {
            Class.forName(conf.get("driver"));
            return DriverManager.getConnection(
                    conf.get("url"),
                    conf.get("username"),
                    conf.get("password")
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
