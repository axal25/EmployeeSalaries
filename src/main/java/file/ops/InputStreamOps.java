package file.ops;

import file.ops.exceptions.BadFileNameException;
import file.ops.exceptions.InputStreamNullException;
import file.ops.exceptions.InputStreamNotOpenException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamOps {
    public static InputStream getNewInputStream(String fileName) throws BadFileNameException, InputStreamNotOpenException, IOException {
        final String functionName = "getNewInputStream(String fileName)";
        if (fileName == null || fileName.isEmpty())
            throw new BadFileNameException(InputStreamOps.class.getSimpleName(), functionName, fileName);
        try {
            InputStream inputStream = InputStreamOps.class.getResourceAsStream(fileName);
            if (inputStream == null)
                throw new InputStreamNullException(InputStreamOps.class.getSimpleName(), functionName, fileName);
            return inputStream;
        } catch(InputStreamNullException e) {
            try {
                return new FileInputStream(new File(fileName));
            } catch (IOException | SecurityException ex) {
                throw new InputStreamNotOpenException(InputStreamOps.class.getSimpleName(), functionName, fileName, e, ex);
            }
        }
    }
}
