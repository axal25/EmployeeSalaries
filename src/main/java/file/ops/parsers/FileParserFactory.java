package file.ops.parsers;

import file.ops.exceptions.BadFileNameException;
import file.ops.parsers.exeptions.BadFileExtensionException;

import java.io.File;

public class FileParserFactory {
    public static final String CSV_EXTENSION = "csv";
    public static final String JSON_EXTENSION = "json";

    public static FileParser getFileParser(File file) throws BadFileExtensionException, BadFileNameException {
        final String functionName = "getExtension(String fileName)";
        final String fileExtension = getFileExtension(file.getPath());
        try {
            switch (fileExtension) {
                case CSV_EXTENSION:
                    return FileParser.CSV;
                case JSON_EXTENSION:
                    return FileParser.JSON;
                default:
                    throw new UnsupportedOperationException(new StringBuilder().append("Unexpected value: ").append(fileExtension).toString());
            }
        } catch(NullPointerException | UnsupportedOperationException e) {
            throw new BadFileExtensionException(FileParser.class.getSimpleName(), functionName, file.getPath(), fileExtension, e);
        }
    }

    protected static String getFileExtension(String fileName) throws BadFileNameException {
        final String functionName = "getFileExtension(String fileName)";
        if(fileName == null || fileName.isEmpty()) throw new BadFileNameException(FileParserFactory.class.getSimpleName(), functionName, fileName, null);
        if(fileName.contains(".")) {
            final int lastIndexOfFileSeparator = fileName.lastIndexOf('.');
            if (lastIndexOfFileSeparator != -1) {
                return fileName.substring(lastIndexOfFileSeparator + 1).toLowerCase();
            }
        }
        return null;
    }
}
