package file.ops.parsers;

import file.ops.parsers.impl.exceptions.ParserImplementationException;
import pojo.JobAverage;

import java.util.HashSet;

public interface IFileParser {
    public HashSet<JobAverage> parse(String fileName) throws ParserImplementationException;
}
