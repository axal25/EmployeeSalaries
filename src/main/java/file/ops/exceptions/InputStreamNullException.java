package file.ops.exceptions;

import exception.DefaultException;

public class InputStreamNullException extends DefaultException {

    private static final Class thisClass = InputStreamNullException.class;
    private static final long serialVersionUID = 7215503489499097076L;

    public InputStreamNullException(
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
                .append("InputStream inputStream == null. FileName: ")
                .append(BadFileNameException.getFileNameToSting(fileName))
                .toString();
    }
}
