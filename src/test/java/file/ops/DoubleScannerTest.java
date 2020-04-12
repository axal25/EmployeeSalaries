package file.ops;

import file.ops.exceptions.*;
import main.Main;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoubleScannerTest {
    public static final String[] EXISTING_CSV_RESOURCE_RELATIVE_PATHS = Main.EXISTING_CSV_RESOURCE_RELATIVE_PATHS;

    @Test
    @Order(1)
    @DisplayName("getNewScanner - EXISTING_CSV_RESOURCE_RELATIVE_PATHS")
    void getNewScanner_EXISTING_RESOURCE_RELATIVE_PATHS() throws BadFileNameException, IOException, DoubleScannerClosedException, DoubleScannerNullDelimiterException, InputStreamNotOpenException {
        for (String existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
            DoubleScanner doubleScanner = new DoubleScanner(existingResourceRelativePath);
            assertNotNull(doubleScanner.getInputStreamScanner());
            assertTrue(doubleScanner.getInputStreamScanner().hasNext());
            assertNull(doubleScanner.getLineScanner());

            // bare-bones usage
            doubleScanner.useDelimiter(";");
            while(doubleScanner.hasNextLine()) {
                doubleScanner.nextLine();
                while(doubleScanner.hasNext()) {
                    doubleScanner.next();
                }
            }

            assertThrows(DoubleScannerNullDelimiterException.class, () -> doubleScanner.useDelimiter((Pattern) null));
            assertThrows(DoubleScannerNullDelimiterException.class, () -> doubleScanner.useDelimiter((String) null));

            doubleScanner.close();

            assertThrows(DoubleScannerClosedException.class, () -> doubleScanner.useDelimiter(";"));
            assertThrows(DoubleScannerClosedException.class, doubleScanner::hasNextLine);
            assertThrows(DoubleScannerClosedException.class, doubleScanner::nextLine);
            assertThrows(DoubleScannerClosedException.class, doubleScanner::hasNext);
            assertThrows(DoubleScannerClosedException.class, doubleScanner::next);
            assertThrows(DoubleScannerClosedException.class, doubleScanner::close);
        }
    }

    @Test
    @Order(2)
    @DisplayName("getNewScanner - AutoCloseable - EXISTING_CSV_RESOURCE_RELATIVE_PATHS")
    void getNewScanner_EXISTING_RESOURCE_RELATIVE_PATHS_AutoCloseable() {
        for (String existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
            assertThrows(DoubleScannerClosedException.class, () -> {
                try( DoubleScanner doubleScanner = new DoubleScanner(existingResourceRelativePath) ) {
                    assertNotNull(doubleScanner.getInputStreamScanner());
                    assertTrue(doubleScanner.getInputStreamScanner().hasNext());
                    assertNull(doubleScanner.getLineScanner());

                    // bare-bones usage
                    doubleScanner.useDelimiter(";");
                    while(doubleScanner.hasNextLine()) {
                        doubleScanner.nextLine();
                        while(doubleScanner.hasNext()) {
                            doubleScanner.next();
                        }
                    }

                    assertThrows(DoubleScannerNullDelimiterException.class, () -> doubleScanner.useDelimiter((Pattern) null));
                    assertThrows(DoubleScannerNullDelimiterException.class, () -> doubleScanner.useDelimiter((String) null));

                    doubleScanner.close();

                    assertThrows(DoubleScannerClosedException.class, () -> doubleScanner.useDelimiter(";"));
                    assertThrows(DoubleScannerClosedException.class, doubleScanner::hasNextLine);
                    assertThrows(DoubleScannerClosedException.class, doubleScanner::nextLine);
                    assertThrows(DoubleScannerClosedException.class, doubleScanner::hasNext);
                    assertThrows(DoubleScannerClosedException.class, doubleScanner::next);
                    assertThrows(DoubleScannerClosedException.class, doubleScanner::close);
                }
            });
        }
    }
}
