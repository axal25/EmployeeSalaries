package file.ops.parsers.impl.csv;

import file.ops.parsers.impl.exceptions.ParserImplementationException;
import main.Main;
import org.junit.jupiter.api.*;
import pojo.JobAverage;

import java.util.HashSet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CsvParserTest {
    public static final String[] EXISTING_CSV_RESOURCE_RELATIVE_PATHS = Main.EXISTING_CSV_RESOURCE_RELATIVE_PATHS;

    @Test
    @Order(1)
    @DisplayName("CsvParser Constructor")
    void csvParserConstructor() {
        for (String existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
            CsvParser csvParser = new CsvParser(existingResourceRelativePath);
        }
    }

    @Test
    @Order(2)
    @DisplayName("CsvParser ParseImpl")
    void csvParserParseImpl() throws ParserImplementationException {
        for (String existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
            CsvParser csvParser = new CsvParser(existingResourceRelativePath);
            HashSet<JobAverage> jobAverages = csvParser.parseImpl();
            System.out.println(existingResourceRelativePath);
            jobAverages.forEach(jobAverage -> System.out.println(new StringBuilder().append(jobAverage.toString()).append(" jobAverage.getAverage(): ").append(jobAverage.getAverage())));
        }
    }
}
