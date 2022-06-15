package jm.task.core.jdbc.conf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfProvider {

    public static final String CONNECTION_CONF = "conf/connection.conf";
    public static final String QUERY_CONF = "conf/query.list";

    public Map<String, String> connection() {
        return getMap(CONNECTION_CONF);
    }

    public Map<String, String> queries() {
        return getMap(QUERY_CONF);
    }

    private Map<String, String> getMap(String filePathName) {
        List<String> lineList = new ConfReader().read(filePathName);
        if (lineList.isEmpty()) {
            return new HashMap<>(0);
        }
        Map<String, String> map = new HashMap<>();
        for (String line : lineList) {
            String[] array = getArray(line);
            if (array.length == 2) {
                map.put(array[0], array[1]);
            }
        }
        return map;
    }

    /**
     * если у нас попадаются строки с несколькими разделителями
     * все последующие (после первого) принимать как включенные в value
     */
    private String[] getArray(String propertyValue) {
        String[] array = propertyValue.split("=");
        if (array.length < 2) {
            return new String[0];
        } else if (array.length == 2) {
            return array;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < array.length; i++) {
            stringBuilder.append(array[i]).append("=");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return new String[]{array[0], stringBuilder.toString()};
    }
}
