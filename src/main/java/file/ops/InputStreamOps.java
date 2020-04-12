package file.ops;

import file.ops.exceptions.BadFileNameException;
import file.ops.exceptions.InputStreamNullException;
import file.ops.exceptions.InputStreamNotOpenException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamOps {
    public static InputStream getNewInputStream(File file) throws BadFileNameException, InputStreamNotOpenException {
        final String functionName = "getNewInputStream(String fileName)";
        if (file == null || file.getPath() == null || file.getPath().isEmpty())
            throw new BadFileNameException(InputStreamOps.class.getSimpleName(), functionName, (file == null ? "file === null" : file.getPath()));
        try {
            InputStream inputStream = InputStreamOps.class.getResourceAsStream(file.getPath());
            if (inputStream == null)
                throw new InputStreamNullException(InputStreamOps.class.getSimpleName(), functionName, file.getPath());
            return inputStream;
        } catch(InputStreamNullException e) {
            try {
                return new FileInputStream(file);
            } catch (IOException | SecurityException ex) {
                throw new InputStreamNotOpenException(InputStreamOps.class.getSimpleName(), functionName, file.getPath(), e, ex);
            }
        }
    }
}
