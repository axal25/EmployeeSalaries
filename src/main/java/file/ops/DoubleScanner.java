package file.ops;

import file.ops.exceptions.*;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

@Getter
public class DoubleScanner implements AutoCloseable {
    private InputStream inputStream;
    private Scanner inputStreamScanner;
    private Scanner lineScanner;
    private boolean isClosed = true;
    private Pattern delimiter = null;

    public DoubleScanner(File file) throws BadFileNameException, InputStreamNotOpenException {
        this.inputStream = InputStreamOps.getNewInputStream(file);
        this.inputStreamScanner = new Scanner(this.inputStream);
        this.isClosed = false;
        this.delimiter = inputStreamScanner.delimiter();
    }

    public void close() throws IOException, DoubleScannerClosedException {
        final String functionName = "close()";
        if(isClosed()) throw new DoubleScannerClosedException(this.getClass().getSimpleName(), functionName);
        if(this.lineScanner != null) this.lineScanner.close();
        this.inputStreamScanner.close();
        this.inputStream.close();
        this.isClosed = true;
    }

    public void useDelimiter(String delimiter) throws DoubleScannerClosedException, DoubleScannerNullDelimiterException {
        final String functionName = "useDelimiter(String delimiter)";
        if(delimiter == null) throw new DoubleScannerNullDelimiterException(this.getClass().getSimpleName(), functionName);
        this.useDelimiter(Pattern.compile(delimiter));
    }

    public void useDelimiter(Pattern delimiter) throws DoubleScannerClosedException, DoubleScannerNullDelimiterException {
        final String functionName = "useDelimiter(Pattern delimiter)";
        if(isClosed()) throw new DoubleScannerClosedException(this.getClass().getSimpleName(), functionName);
        if(delimiter == null) throw new DoubleScannerNullDelimiterException(this.getClass().getSimpleName(), functionName);
        this.delimiter = delimiter;
        if(this.lineScanner != null) this.lineScanner.useDelimiter(delimiter);
    }

    public boolean hasNextLine() throws DoubleScannerClosedException {
        final String functionName = "hasNextLine()";
        if(isClosed()) throw new DoubleScannerClosedException(this.getClass().getSimpleName(), functionName);
        return this.inputStreamScanner.hasNextLine();
    }

    public String nextLine() throws DoubleScannerClosedException, DoubleScannerNullDelimiterException {
        final String functionName = "nextLine()";
        if(isClosed()) throw new DoubleScannerClosedException(this.getClass().getSimpleName(), functionName);
        String nextLine = this.inputStreamScanner.nextLine();
        this.lineScanner = new Scanner(nextLine);
        useDelimiter(this.delimiter);
        return nextLine;
    }

    public boolean hasNext() throws DoubleScannerClosedException, DoubleScannerNullDelimiterException {
        final String functionName = "hasNext()";
        if(isClosed()) throw new DoubleScannerClosedException(this.getClass().getSimpleName(), functionName);
        if(this.lineScanner == null) {
            if(this.hasNextLine()) this.nextLine();
            else return false;
        }
        return this.lineScanner.hasNext();
    }

    public String next() throws DoubleScannerClosedException {
        final String functionName = "next()";
        if(isClosed()) throw new DoubleScannerClosedException(this.getClass().getSimpleName(), functionName);
        return this.lineScanner.next();
    }
}
