package jm.task.core.jdbc.connection;

import java.util.Map;

public class SettingsSingleton {

    private static SettingsSingleton instance;
    private final Map<String, String> map;

    private SettingsSingleton(Map<String, String> map) {
        this.map = map;
    }

    public static SettingsSingleton instance(Map<String, String> map) {
        if (instance == null) {
            instance = new SettingsSingleton(null);
        }
        if (map != null && !map.isEmpty()) {
            instance = new SettingsSingleton(map);
        }
        return instance;
    }

    public Map<String, String> getSettings() {
        return map;
    }
}
