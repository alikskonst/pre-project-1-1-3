package jm.task.core.jdbc.conf;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

class ConfReader {

    List<String> read(String filePathName) {
        List<String> lineList = readAllLines(filePathName);
        lineList.removeIf(line -> line.startsWith("#"));
        return lineList;
    }

    private List<String> readAllLines(String filePathName) {
        try {
            return Files.readAllLines(Paths.get(filePathName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
