package file.ops.parsers.impl.json.strategy;

import file.ops.parsers.IFileParser;
import file.ops.parsers.impl.exceptions.ParserImplementationException;
import pojo.JobAverage;

import java.util.HashSet;

public interface IParse extends IFileParser {
    /**
     * keep for readability
     * not needed since it's already inherited
     * inherited from IFileParser
     */

    @Override
    public HashSet<JobAverage> parse(String fileName) throws ParserImplementationException;
}
