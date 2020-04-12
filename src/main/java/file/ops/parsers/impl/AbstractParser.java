package file.ops.parsers.impl;

import file.ops.parsers.impl.exceptions.ParserImplementationException;
import lombok.Getter;
import pojo.JobAverage;

import java.util.HashSet;

@Getter
public abstract class AbstractParser {
    private final String fileName;

    protected AbstractParser() {
        throw new IllegalStateException("Not for use");
    }

    public AbstractParser(String fileName) {
        this.fileName = fileName;
    }

    public abstract HashSet<JobAverage> parseImpl() throws ParserImplementationException;
}
