package file.ops.parsers.impl;

import file.ops.parsers.impl.exceptions.ParserImplementationException;
import lombok.Getter;
import pojo.JobAverage;

import java.io.File;
import java.util.HashSet;

@Getter
public abstract class AbstractParser {
    private final File file;

    protected AbstractParser() {
        throw new IllegalStateException("Not for use");
    }

    public AbstractParser(File file) {
        this.file = file;
    }

    public abstract HashSet<JobAverage> parseImpl() throws ParserImplementationException;
}
