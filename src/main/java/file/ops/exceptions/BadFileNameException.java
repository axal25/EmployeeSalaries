package file.ops.exceptions;

import exception.DefaultException;

public class BadFileNameException extends DefaultException {

    private static final Class thisClass = BadFileNameException.class;
    private static final long serialVersionUID = 5918179103072210378L;

    public BadFileNameException(
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
                .append("Bad fileName: ")
                .append(getFileNameToSting(fileName))
                .toString();
    }

    public static String getFileNameToSting(String fileName) {
        return (fileName == null) ? "null" : ("\"" + fileName + "\"");
    }
}
