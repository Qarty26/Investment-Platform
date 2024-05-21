package Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Audit {

    private final String auditFile;
    private final Map<String, Integer> operationCount;

    public Audit(String auditFile) {
        this.auditFile = auditFile;
        this.operationCount = new HashMap<>();
    }

    public void logOperation(String operation) {
        operationCount.put(operation, operationCount.getOrDefault(operation, 0) + 1);
        writeToCSV();
    }

    private void writeToCSV() {
        try (FileWriter writer = new FileWriter(auditFile)) {
            writer.append("Operation,Times\n");
            for (Map.Entry<String, Integer> entry : operationCount.entrySet()) {
                writer.append(entry.getKey())
                        .append(",")
                        .append(String.valueOf(entry.getValue()))
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
