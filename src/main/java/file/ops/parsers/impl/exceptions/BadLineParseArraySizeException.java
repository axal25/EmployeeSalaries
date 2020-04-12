package file.ops.parsers.impl.exceptions;

import exception.DefaultException;
import file.ops.parsers.impl.csv.CsvParser;

public class BadLineParseArraySizeException extends DefaultException {

    private static final Class thisClass = BadLineParseArraySizeException.class;
    private static final long serialVersionUID = 8225270693292890476L;

    public BadLineParseArraySizeException(
            String callingClassName,
            String callingFunctionName,
            int arraySize,
            Throwable... causeThrowables
    ) {
        super(
                getExceptionDetectedAppendedByCauseThrowableMessages(callingClassName, callingFunctionName, thisClass, getDetectedCause(arraySize), causeThrowables),
                new Throwable(
                        getExceptionDetectedAppendedByCauseThrowableMessages(callingClassName, callingFunctionName, thisClass, getDetectedCause(arraySize), causeThrowables)
                )
        );
    }

    private static String getDetectedCause(int arraySize) {
        return new StringBuilder()
                .append("Array produced by parsing line is wrong size. Detected size: ")
                .append(arraySize)
                .append(" vs. expected line parse array size: ")
                .append(CsvParser.EXPECTED_LINE_PARSE_ARRAY_SIZE)
                .toString();
    }
}
