package main;

import org.junit.jupiter.api.*;

import java.io.File;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainTest {

    public static final File[] EXISTING_CSV_RESOURCE_RELATIVE_PATHS = Main.EXISTING_CSV_RESOURCE_RELATIVE_PATHS;
    public static final File[] EXISTING_JSON_RESOURCE_RELATIVE_PATHS = Main.EXISTING_JSON_RESOURCE_RELATIVE_PATHS;
    public static final File[] BAD_RESOURCE_RELATIVE_PATHS = new File[]{
            new File(""),
    };

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    @DisplayName("Main.main(null)")
    void Main_main_null() {
        Main.main(null);
    }

    @Test
    @Order(2)
    @DisplayName("Main.main(new String[0])")
    void Main_main_emptyArray() {
        Main.main(new String[0]);
    }

    @Test
    @Order(3)
    @DisplayName("Main.main(new String[]{null})")
    void Main_main_nullArrayCell() {
        Main.main(new String[]{null});
    }

    @Test
    @Order(4)
    @DisplayName("Main.main(new String[]{\"\"})")
    void Main_main_emptyArrayCell() {
        Main.main(new String[]{""});
    }

    @Test
    @Order(5)
    @DisplayName("Main.main(EXISTING_CSV_RESOURCE_RELATIVE_PATHS)")
    void Main_main_Csv() {
        Main.main(getFilePaths(EXISTING_CSV_RESOURCE_RELATIVE_PATHS));
    }

    private String[] getFilePaths(File[] files) {
        String[] filePaths = new String[files.length];
        for(int i = 0; i < files.length; i++) {
            filePaths[i] = files[i].getPath();
        }
        return filePaths;
    }

    @Test
    @Order(6)
    @DisplayName("Main.main(EXISTING_JSON_RESOURCE_RELATIVE_PATHS)")
    void Main_main_Json() {
        Main.main(getFilePaths(EXISTING_JSON_RESOURCE_RELATIVE_PATHS));
    }

    @Test
    @Order(7)
    @DisplayName("Main.main(EXISTING_CSV_RESOURCE_RELATIVE_PATHS ++ EXISTING_JSON_RESOURCE_RELATIVE_PATHS)")
    void Main_main_Csv_and_Json() {
        File[] existingCombinedResourceRelativePaths = new File[
                EXISTING_CSV_RESOURCE_RELATIVE_PATHS.length +
                        EXISTING_JSON_RESOURCE_RELATIVE_PATHS.length +
                        BAD_RESOURCE_RELATIVE_PATHS.length
                ];
        for (int i = 0, j = 0, k = 0; i < EXISTING_CSV_RESOURCE_RELATIVE_PATHS.length && j < EXISTING_JSON_RESOURCE_RELATIVE_PATHS.length; i++, j++, k++) {
            existingCombinedResourceRelativePaths[i] = EXISTING_CSV_RESOURCE_RELATIVE_PATHS[i];
            existingCombinedResourceRelativePaths[EXISTING_CSV_RESOURCE_RELATIVE_PATHS.length + j] = EXISTING_JSON_RESOURCE_RELATIVE_PATHS[j];
            if(k < BAD_RESOURCE_RELATIVE_PATHS.length)
                existingCombinedResourceRelativePaths[EXISTING_CSV_RESOURCE_RELATIVE_PATHS.length + EXISTING_JSON_RESOURCE_RELATIVE_PATHS.length + k] = BAD_RESOURCE_RELATIVE_PATHS[k];
        }
        Main.main(getFilePaths(existingCombinedResourceRelativePaths));
    }
}
