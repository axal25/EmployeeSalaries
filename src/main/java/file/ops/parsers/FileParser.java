package file.ops.parsers;

import file.ops.parsers.impl.exceptions.ParserImplementationException;
import file.ops.parsers.impl.csv.CsvParser;
import file.ops.parsers.impl.json.JsonParser;
import pojo.JobAverage;

import java.io.File;
import java.util.HashSet;

public enum FileParser implements IFileParser {
    CSV{
        @Override
        public HashSet<JobAverage> parse(File file) throws ParserImplementationException {
            CsvParser csvParser = new CsvParser(file);
            return csvParser.parseImpl();
        }
    }, JSON{
        @Override
        public HashSet<JobAverage> parse(File file) throws ParserImplementationException {
            JsonParser jsonParser = new JsonParser(file);
            return jsonParser.parseImpl();
        }
    };

    @Override
    public HashSet<JobAverage> parse(File file) throws ParserImplementationException {
        throw new UnsupportedOperationException("Not implemented functionality for this type of file.");
    }
}
