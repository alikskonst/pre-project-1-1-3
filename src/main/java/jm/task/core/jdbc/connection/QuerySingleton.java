package jm.task.core.jdbc.connection;

import java.util.HashMap;
import java.util.Map;

public class QuerySingleton {

    private static QuerySingleton instance;
    private final Map<String, String> queryMap;

    private QuerySingleton(Map<String, String> queryMap) {
        this.queryMap = queryMap;
    }

    public static QuerySingleton instance() {
        if (instance == null) {
            instance = new QuerySingleton(new HashMap<>(0));
        }
        return instance;
    }

    public Map<String, String> getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(Map<String, String> queryMap) {
        instance = new QuerySingleton(queryMap);
    }

    public String getQuery(String key) {
        return queryMap.getOrDefault(key, "");
    }
}
