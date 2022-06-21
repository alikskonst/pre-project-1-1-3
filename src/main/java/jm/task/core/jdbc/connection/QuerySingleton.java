package jm.task.core.jdbc.connection;

import java.util.Collections;
import java.util.Map;

public class QuerySingleton {

    private static QuerySingleton instance;
    private final Map<String, String> queryMap;

    private QuerySingleton(Map<String, String> queryMap) {
        this.queryMap = queryMap;
    }

    public static QuerySingleton instance(Map<String, String> queryMap) {
        if (instance == null) {
            instance = new QuerySingleton(Collections.emptyMap());
        }
        if (queryMap != null && !queryMap.isEmpty()) {
            instance = new QuerySingleton(queryMap);
        }
        return instance;
    }

    public Map<String, String> getQueryMap() {
        return queryMap;
    }

    public String getQuery(String key) {
        return queryMap.getOrDefault(key, "");
    }
}
