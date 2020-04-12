package file.ops.parsers.impl.exceptions;

import exception.DefaultException;
import file.ops.exceptions.BadFileNameException;

public class ParserImplementationException extends DefaultException {

    private static final Class thisClass = ParserImplementationException.class;
    private static final long serialVersionUID = -6296529445571373952L;

    public ParserImplementationException(
            String callingClassName,
            String callingFunctionName,
            String fileName,
            Throwable... causeThrowables
    ) {
        super(
                getExceptionDetectedAppendedByCauseThrowableMessages(callingClassName, callingFunctionName, thisClass, getDetectedCause(fileName), causeThrowables),
                new Throwable(
                        getExceptionDetectedAppendedByCauseThrowableMessages(callingClassName, callingFunctionName, thisClass, getDetectedCause(fileName), causeThrowables)
                )
        );
    }

    private static String getDetectedCause(String fileName) {
        return new StringBuilder()
                .append("Could not parse file. FileName: ")
                .append(BadFileNameException.getFileNameToSting(fileName))
                .toString();
    }
}
