package file.ops;

import file.ops.exceptions.BadFileNameException;
import file.ops.exceptions.InputStreamNotOpenException;
import main.Main;
import main.MainTest;
import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InputStreamOpsTest {

    public static final String[] EXISTING_CSV_RESOURCE_RELATIVE_PATHS = Main.EXISTING_CSV_RESOURCE_RELATIVE_PATHS;
    public static final String[] EXISTING_JSON_RESOURCE_RELATIVE_PATHS = Main.EXISTING_JSON_RESOURCE_RELATIVE_PATHS;
    public static final String[] NOT_EXISTING_RESOURCE_RELATIVE_PATHS = {
            new StringBuilder().append(File.separator).append("csv").append(File.separator).append("employeesNotExisting.csv").toString(),
            new StringBuilder().append(File.separator).append("json").append(File.separator).append("employeesNotExisting.json").toString(),
            new StringBuilder().append(File.separator).append("csv").append(File.separator).append("employees with spaces Not Existing.csv").toString(),
            new StringBuilder().append(File.separator).append("json").append(File.separator).append("employees with spaces Not Existing.json").toString(),
            new StringBuilder().append(File.separator).append("test directory with spaces Not Existing").append(File.separator).append("employees.csv").toString(),
            new StringBuilder().append(File.separator).append("test directory with spaces Not Existing").append(File.separator).append("employees.json").toString(),
            new StringBuilder().append(File.separator).append("test directory with spaces Not Existing").append(File.separator).append("employees with spaces.csv").toString(),
            new StringBuilder().append(File.separator).append("test directory with spaces Not Existing").append(File.separator).append("employees with spaces.json").toString(),
    };
    public static final String[] BAD_RESOURCE_RELATIVE_PATHS = MainTest.BAD_RESOURCE_RELATIVE_PATHS;

    @Test
    @Order(1)
    @DisplayName("getNewInputStream - EXISTING_CSV / JSON_RESOURCE_RELATIVE_PATHS")
    void getNewInputStream_EXISTING_RESOURCE_RELATIVE_PATHS() throws BadFileNameException, IOException, InputStreamNotOpenException {
        for (String existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
            InputStream inputStream = InputStreamOps.getNewInputStream(existingResourceRelativePath);
            assertNotNull(inputStream);
            inputStream.close();
        }
        for (String existingResourceRelativePath : EXISTING_JSON_RESOURCE_RELATIVE_PATHS) {
            InputStream inputStream = InputStreamOps.getNewInputStream(existingResourceRelativePath);
            assertNotNull(inputStream);
            inputStream.close();
        }
    }

    @Test
    @Order(2)
    @DisplayName("getNewInputStream - BAD_RESOURCE_RELATIVE_PATHS")
    void getNewInputStream_BAD_RESOURCE_RELATIVE_PATHS() {
        for (String badResourceRelativePath : BAD_RESOURCE_RELATIVE_PATHS) {
            final String finalExistingResourceRelativePath = badResourceRelativePath;
            assertThrows(
                    BadFileNameException.class,
                    () -> InputStreamOps.getNewInputStream(finalExistingResourceRelativePath)
            );
        }
    }

    @Test
    @Order(3)
    @DisplayName("getNewInputStream - NOT_EXISTING_RESOURCE_RELATIVE_PATHS")
    void getNewInputStream_NOT_EXISTING_RESOURCE_RELATIVE_PATHS() {
        for (String notExistingResourceRelativePath : NOT_EXISTING_RESOURCE_RELATIVE_PATHS) {
            final String finalExistingResourceRelativePath = notExistingResourceRelativePath;
            assertThrows(
                    InputStreamNotOpenException.class,
                    () -> InputStreamOps.getNewInputStream(finalExistingResourceRelativePath)
            );
        }
    }
}
