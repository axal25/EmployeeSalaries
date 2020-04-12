package file.ops.exceptions;

import exception.DefaultException;

public class DoubleScannerClosedException extends DefaultException {

    private static final Class thisClass = DoubleScannerClosedException.class;
    private static final long serialVersionUID = 6996274329680665109L;

    public DoubleScannerClosedException(
            String callingClassName,
            String callingFunctionName,
            Throwable... causeThrowables
    ) {
        super(
                getExceptionDetectedAppendedByCauseThrowableMessages(callingClassName, callingFunctionName, thisClass, getDetectedCause(), causeThrowables),
                new Throwable(
                        getExceptionDetectedAppendedByCauseThrowableMessages(callingClassName, callingFunctionName, thisClass, getDetectedCause(), causeThrowables)
                )
        );
    }

    private static String getDetectedCause() {
        return new StringBuilder()
                .append("This DoubleScanner instance has been already closed.")
                .toString();
    }

}
