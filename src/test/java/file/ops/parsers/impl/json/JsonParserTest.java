package file.ops.parsers.impl.json;

import file.ops.parsers.impl.exceptions.ParserImplementationException;
import main.Main;
import org.junit.jupiter.api.*;
import pojo.JobAverage;

import java.util.HashSet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JsonParserTest {
    public static final String[] EXISTING_JSON_RESOURCE_RELATIVE_PATHS = Main.EXISTING_JSON_RESOURCE_RELATIVE_PATHS;

    @Test
    @Order(1)
    @DisplayName("JsonParser Constructor")
    void csvParserConstructor() {
        for (String existingResourceRelativePath : EXISTING_JSON_RESOURCE_RELATIVE_PATHS) {
            JsonParser jsonParser = new JsonParser(existingResourceRelativePath);
        }
    }

    @Test
    @Order(2)
    @DisplayName("JsonParser ParseImpl")
    void csvParserParseImpl() throws ParserImplementationException {
        for (String existingResourceRelativePath : EXISTING_JSON_RESOURCE_RELATIVE_PATHS) {
            JsonParser jsonParser = new JsonParser(existingResourceRelativePath);
            HashSet<JobAverage> jobAverages = jsonParser.parseImpl();
            System.out.println(existingResourceRelativePath);
            jobAverages.forEach(jobAverage -> System.out.println(new StringBuilder().append(jobAverage.toString()).append(" jobAverage.getAverage(): ").append(jobAverage.getAverage())));
        }
    }
}
