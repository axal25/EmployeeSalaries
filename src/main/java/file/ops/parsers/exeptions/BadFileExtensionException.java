package file.ops.parsers.exeptions;

import exception.DefaultException;
import file.ops.exceptions.BadFileNameException;

public class BadFileExtensionException extends DefaultException {

    public BadFileExtensionException(
            String callingClassName,
            String callingFunctionName,
            String fileName,
            String fileExtension,
            Throwable... causeThrowables
    ) {
        super(
                getExceptionDetectedAppendedByCauseThrowableMessages(
                        callingClassName,
                        callingFunctionName,
                        BadFileNameException.class,
                        getDetectedCause(fileName, fileExtension),
                        causeThrowables
                ),
                new Throwable(
                        getExceptionDetectedAppendedByCauseThrowableMessages(
                                callingClassName,
                                callingFunctionName,
                                BadFileNameException.class,
                                getDetectedCause(fileName, fileExtension),
                                causeThrowables
                        )
                )
        );
    }

    private static String getDetectedCause(String fileName, String fileExtension) {
        return new StringBuilder()
                .append("Bad fileName: ")
                .append(getFileNameToSting(fileName))
                .append(". Resulting in bad file extension: ")
                .append(getFileNameToSting(fileExtension))
                .toString();
    }

    static String getFileNameToSting(String fileName) {
        return (fileName == null) ? "null" : ("\"" + fileName + "\"");
    }
}
