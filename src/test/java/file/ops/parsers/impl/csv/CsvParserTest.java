package file.ops.parsers.impl.csv;

import file.ops.parsers.impl.exceptions.ParserImplementationException;
import main.Main;
import main.MainTest;
import org.junit.jupiter.api.*;
import pojo.JobAverage;

import java.io.File;
import java.util.HashSet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CsvParserTest {
    public static final File[] EXISTING_CSV_RESOURCE_RELATIVE_PATHS = Main.EXISTING_CSV_RESOURCE_RELATIVE_PATHS;

    @Test
    @Order(1)
    @DisplayName("CsvParser Constructor")
    void csvParserConstructor() {
        for (File existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
            CsvParser csvParser = new CsvParser(existingResourceRelativePath);
        }
    }

    @Test
    @Order(2)
    @DisplayName("CsvParser ParseImpl")
    void csvParserParseImpl() throws ParserImplementationException {
        for (File existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
            CsvParser csvParser = new CsvParser(existingResourceRelativePath);
            HashSet<JobAverage> jobAverages = csvParser.parseImpl();
            if(MainTest.DO_PRINT_TEST_DATA_TO_SYSTEM_OUT)
                System.out.println(existingResourceRelativePath);
            if(MainTest.DO_PRINT_TEST_DATA_TO_SYSTEM_OUT)
                jobAverages.forEach(jobAverage -> System.out.println(new StringBuilder().append(jobAverage.toString()).append(" jobAverage.getAverage(): ").append(jobAverage.getAverage())));
        }
    }
}
