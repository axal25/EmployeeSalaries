package file.ops.parsers;

import file.ops.exceptions.BadFileNameException;
import file.ops.parsers.exeptions.BadFileExtensionException;
import file.ops.parsers.impl.exceptions.ParserImplementationException;
import main.Main;
import org.junit.jupiter.api.*;
import pojo.JobAverage;

import java.io.File;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileParserFactoryTest {
    public static final File[] EXISTING_CSV_RESOURCE_RELATIVE_PATHS = Main.EXISTING_CSV_RESOURCE_RELATIVE_PATHS;
    public static final File[] EXISTING_JSON_RESOURCE_RELATIVE_PATHS = Main.EXISTING_JSON_RESOURCE_RELATIVE_PATHS;
    public static final File[] UNSUPPORTED_EXTENSIONS = {
            new File(""),
            new File("txt"),
            new File(".txt"),
            new File("bla.txt")
    };
    public static final String[][] EXPECTED_JOB_AVERAGES_TO_STRING = {
            {
                "JobAverage{amountOfEntries=1, job=Job{jobTitle='Priest', salary=PLN 15240}} jobAverage.getAverage(): PLN 15240",
                "JobAverage{amountOfEntries=2, job=Job{jobTitle='Janitor', salary=PLN 26920.9}} jobAverage.getAverage(): PLN 13460.45",
                "JobAverage{amountOfEntries=2, job=Job{jobTitle='Teacher', salary=PLN 6240.3}} jobAverage.getAverage(): PLN 3120.15",
            }, {
                "JobAverage{amountOfEntries=2, job=Job{jobTitle='Priest', salary=PLN 34480}} jobAverage.getAverage(): PLN 17240",
                "JobAverage{amountOfEntries=3, job=Job{jobTitle='Janitor', salary=PLN 42381.35}} jobAverage.getAverage(): PLN 14127.11666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666667",
                "JobAverage{amountOfEntries=3, job=Job{jobTitle='Teacher', salary=PLN 10480.9}} jobAverage.getAverage(): PLN 3493.633333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333",
                "JobAverage{amountOfEntries=1, job=Job{jobTitle='Goddess', salary=PLN 643460.45}} jobAverage.getAverage(): PLN 643460.45",
                "JobAverage{amountOfEntries=1, job=Job{jobTitle='Priestess', salary=PLN 36700.1}} jobAverage.getAverage(): PLN 36700.1",
            },
    };

    public static String getExpectedJobAveragesToString(int sourceFileIndex) {
        StringBuilder jobAveragesToString = new StringBuilder();
        int expectedJobAveragesToStringIndex = Integer.MIN_VALUE;
        if(sourceFileIndex < Main.DIFFERENT_CONTENTS_INDEX) expectedJobAveragesToStringIndex = 0;
        else expectedJobAveragesToStringIndex = 1;
        for (String jobAverageToString : EXPECTED_JOB_AVERAGES_TO_STRING[expectedJobAveragesToStringIndex]) {
            jobAveragesToString.append(jobAverageToString).append("\n\r");
        }
        return jobAveragesToString.toString();
    }

    @Test
    @Order(1)
    @DisplayName("FileParserFactory Csv Constructor")
    void FileParserFactoryCsvConstructor() throws BadFileExtensionException, BadFileNameException {
        for (File existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
            String fileExtension = FileParserFactory.getFileExtension(existingResourceRelativePath.getPath());
            assertEquals("csv", fileExtension);
            FileParser fileParser = FileParserFactory.getFileParser(existingResourceRelativePath);
            assertEquals(FileParser.CSV, fileParser);
        }
    }

    @Test
    @Order(2)
    @DisplayName("FileParserFactory Json Constructor")
    void FileParserFactoryJsonConstructor() throws BadFileExtensionException, BadFileNameException {
        for (File existingResourceRelativePath : EXISTING_JSON_RESOURCE_RELATIVE_PATHS) {
            String fileExtension = FileParserFactory.getFileExtension(existingResourceRelativePath.getPath());
            assertEquals("json", fileExtension);
            FileParser fileParser = FileParserFactory.getFileParser(existingResourceRelativePath);
            assertEquals(FileParser.JSON, fileParser);
        }
    }

    @Test
    @Order(3)
    @DisplayName("FileParserFactory Unsupported Constructor")
    void FileParserFactoryUnsupportedConstructor() {
        for (File unsupportedExtension : UNSUPPORTED_EXTENSIONS) {
            if(unsupportedExtension.getPath() == null || unsupportedExtension.getPath().isEmpty()) assertThrows(BadFileNameException.class, () -> FileParserFactory.getFileParser(unsupportedExtension));
            else assertThrows(BadFileExtensionException.class, () -> FileParserFactory.getFileParser(unsupportedExtension));
        }
    }

    @Test
    @Order(4)
    @DisplayName("FileParserFactory Csv Parse")
    void FileParserFactoryCsvParse() throws BadFileExtensionException, BadFileNameException, ParserImplementationException {
        for (File existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
            String fileExtension = FileParserFactory.getFileExtension(existingResourceRelativePath.getPath());
            assertEquals("csv", fileExtension);
            FileParser fileParser = FileParserFactory.getFileParser(existingResourceRelativePath);
            assertEquals(FileParser.CSV, fileParser);
            HashSet<JobAverage> jobAverages = fileParser.parse(existingResourceRelativePath);
            StringBuilder jobAveragesStringBuilder = new StringBuilder();
            var ref = new Object() {
                int sourceFileIndex = 0;
            };
            jobAverages.forEach(jobAverage -> {
                jobAveragesStringBuilder.append(jobAverage.toString()).append(" jobAverage.getAverage(): ").append(jobAverage.getAverage()).append("\n\r");
                ref.sourceFileIndex++;
            });
            assertEquals(getExpectedJobAveragesToString(ref.sourceFileIndex), jobAveragesStringBuilder.toString());
        }
    }

    @Test
    @Order(5)
    @DisplayName("FileParserFactory Json Parse")
    void FileParserFactoryJsonParse() throws BadFileExtensionException, BadFileNameException, ParserImplementationException {
        for (File existingResourceRelativePath : EXISTING_JSON_RESOURCE_RELATIVE_PATHS) {
            String fileExtension = FileParserFactory.getFileExtension(existingResourceRelativePath.getPath());
            assertEquals("json", fileExtension);
            FileParser fileParser = FileParserFactory.getFileParser(existingResourceRelativePath);
            assertEquals(FileParser.JSON, fileParser);
            HashSet<JobAverage> jobAverages = fileParser.parse(existingResourceRelativePath);
            StringBuilder jobAveragesStringBuilder = new StringBuilder();
            var ref = new Object() {
                int sourceFileIndex = 0;
            };
            jobAverages.forEach(jobAverage -> {
                jobAveragesStringBuilder.append(jobAverage.toString()).append(" jobAverage.getAverage(): ").append(jobAverage.getAverage()).append("\n\r");
                ref.sourceFileIndex++;
            });
            assertEquals(getExpectedJobAveragesToString(ref.sourceFileIndex), jobAveragesStringBuilder.toString());
        }
    }
}
