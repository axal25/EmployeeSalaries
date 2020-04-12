package file.ops.exceptions;

import exception.DefaultException;

public class InputStreamNotOpenException extends DefaultException {

    private static final Class thisClass = InputStreamNotOpenException.class;
    private static final long serialVersionUID = 6326154365289668062L;

    public InputStreamNotOpenException(
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
                .append("Could not open InputStream. FileName: ")
                .append(BadFileNameException.getFileNameToSting(fileName))
                .toString();
    }
}
