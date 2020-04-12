package file.ops.parsers;

import file.ops.parsers.impl.exceptions.ParserImplementationException;
import file.ops.parsers.impl.csv.CsvParser;
import file.ops.parsers.impl.json.JsonParser;
import pojo.JobAverage;

import java.util.HashSet;

public enum FileParser implements IFileParser {
    CSV{
        @Override
        public HashSet<JobAverage> parse(String fileName) throws ParserImplementationException {
            CsvParser csvParser = new CsvParser(fileName);
            return csvParser.parseImpl();
        }
    }, JSON{
        @Override
        public HashSet<JobAverage> parse(String fileName) throws ParserImplementationException {
            JsonParser jsonParser = new JsonParser(fileName);
            return jsonParser.parseImpl();
        }
    };

    @Override
    public HashSet<JobAverage> parse(String fileName) throws ParserImplementationException {
        throw new UnsupportedOperationException("Not implemented functionality for this type of file.");
    }
}
