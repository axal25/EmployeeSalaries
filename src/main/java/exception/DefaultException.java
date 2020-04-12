package exception;

public abstract class DefaultException extends Exception {

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    protected static <T extends Exception> String getExceptionWholeMessage(
            String callingClassName,
            String callingFunctionName,
            Class<T> thisClass,
            String detectedCause) {
        return new StringBuilder()
                .append(getExceptionStartMessage(callingClassName, callingFunctionName, thisClass))
                .append(detectedCause)
                .toString();
    }

    private static <T extends Exception> String getExceptionStartMessage(String callingClassName, String callingFunctionName, Class<T> thisClass) {
        return new StringBuilder()
                .append(callingClassName)
                .append(".")
                .append(callingFunctionName)
                .append(": ")
                .append(thisClass.getSimpleName())
                .append(": ")
                .append("\n\r")
                .append("Exception detected cause: ")
                .toString();
    }

    protected static <T extends Exception> String getExceptionDetectedAppendedByCauseThrowableMessages(
            String callingClassName,
            String callingFunctionName,
            Class<T> thisClass,
            String detectedCause,
            Throwable... causeThrowables
    ) {
        return new StringBuilder()
                .append(getExceptionWholeMessage(callingClassName, callingFunctionName, thisClass, detectedCause))
                .append(getCauseThrowableMessages(causeThrowables))
                .toString();
    }

    private static String getCauseThrowableMessages(Throwable... causeThrowables) {
        StringBuilder causeThrowableMessages = new StringBuilder();
        if(causeThrowables != null) for(Throwable causeThrowable : causeThrowables) causeThrowableMessages.append(getCauseThrowableMessage(causeThrowable));
        return causeThrowableMessages.toString();
    }

    private static String getCauseThrowableMessage(Throwable causeThrowable) {
        return (causeThrowable == null) ? "" : new StringBuilder().append("\n\r").append(causeThrowable.getMessage()).toString();
    }
}
