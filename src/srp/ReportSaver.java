package srp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReportSaver {
    public void save(String reportText, Path destination) throws IOException {
        Files.writeString(destination, reportText);
    }
}