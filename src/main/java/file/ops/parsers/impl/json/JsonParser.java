package file.ops.parsers.impl.json;

import file.ops.parsers.impl.AbstractParser;
import file.ops.parsers.impl.exceptions.ParserImplementationException;
import file.ops.parsers.impl.json.strategy.IParse;
import file.ops.parsers.impl.json.strategy.ParseStrategyFactory;
import pojo.JobAverage;

import java.io.File;
import java.util.HashSet;

public class JsonParser extends AbstractParser {
    private IParse parseImplementation;

    private JsonParser() {
        super();
    }

    public JsonParser(File file) {
        super(file);
        this.parseImplementation = ParseStrategyFactory.getParseStrategyImplementation(ParseStrategyFactory.getRandomParseStrategyType());
    }

    @Override
    public HashSet<JobAverage> parseImpl() throws ParserImplementationException {
        return parseImplementation.parse(super.getFile());
    }
}
