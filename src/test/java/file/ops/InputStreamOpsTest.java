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

    public static final File EXISTING_CSV_RESOURCE_DIRECTORY_RELATIVE_PATHS = Main.EXISTING_CSV_RESOURCE_DIRECTORY_RELATIVE_PATHS;
    public static final File EXISTING_JSON_RESOURCE_DIRECTORY_RELATIVE_PATHS = Main.EXISTING_JSON_RESOURCE_DIRECTORY_RELATIVE_PATHS;
    public static final File EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS = new File("test directory with spaces Not Existing");

    public static final File[] EXISTING_CSV_RESOURCE_RELATIVE_PATHS = Main.EXISTING_CSV_RESOURCE_RELATIVE_PATHS;
    public static final File[] EXISTING_JSON_RESOURCE_RELATIVE_PATHS = Main.EXISTING_JSON_RESOURCE_RELATIVE_PATHS;
    public static final File[] NOT_EXISTING_RESOURCE_RELATIVE_PATHS = {
            new File(EXISTING_CSV_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employeesNotExisting.csv"),
            new File(EXISTING_JSON_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employeesNotExisting.json"),
            new File(EXISTING_CSV_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with spaces Not Existing.csv"),
            new File(EXISTING_JSON_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with spaces Not Existing.json"),
            new File(EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees.csv"),
            new File(EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees.json"),
            new File(EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with spaces.csv"),
            new File(EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with spaces.json"),
    };
    public static final File[] BAD_RESOURCE_RELATIVE_PATHS = MainTest.BAD_RESOURCE_RELATIVE_PATHS;

    @Test
    @Order(1)
    @DisplayName("getNewInputStream - EXISTING_CSV / JSON_RESOURCE_RELATIVE_PATHS")
    void getNewInputStream_EXISTING_RESOURCE_RELATIVE_PATHS() throws BadFileNameException, IOException, InputStreamNotOpenException {
        for (File existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
            InputStream inputStream = InputStreamOps.getNewInputStream(existingResourceRelativePath);
            assertNotNull(inputStream);
            inputStream.close();
        }
        for (File existingResourceRelativePath : EXISTING_JSON_RESOURCE_RELATIVE_PATHS) {
            InputStream inputStream = InputStreamOps.getNewInputStream(existingResourceRelativePath);
            assertNotNull(inputStream);
            inputStream.close();
        }
    }

    @Test
    @Order(2)
    @DisplayName("getNewInputStream - BAD_RESOURCE_RELATIVE_PATHS")
    void getNewInputStream_BAD_RESOURCE_RELATIVE_PATHS() {
        for (File badResourceRelativePath : BAD_RESOURCE_RELATIVE_PATHS) {
            final File finalExistingResourceRelativePath = badResourceRelativePath;
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
        for (File notExistingResourceRelativePath : NOT_EXISTING_RESOURCE_RELATIVE_PATHS) {
            final File finalExistingResourceRelativePath = notExistingResourceRelativePath;
            assertThrows(
                    InputStreamNotOpenException.class,
                    () -> InputStreamOps.getNewInputStream(finalExistingResourceRelativePath)
            );
        }
    }
}
