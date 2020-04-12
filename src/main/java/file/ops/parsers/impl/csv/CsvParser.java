package file.ops.parsers.impl.csv;

import file.ops.DoubleScanner;
import file.ops.exceptions.*;
import file.ops.parsers.impl.AbstractParser;
import file.ops.parsers.impl.exceptions.ParserImplementationException;
import file.ops.parsers.impl.exceptions.BadLineParseArraySizeException;
import pojo.Employee;
import pojo.JobAverage;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

public class CsvParser extends AbstractParser {

    public static final int EXPECTED_LINE_PARSE_ARRAY_SIZE = 5;

    private CsvParser() {
        super();
    }

    public CsvParser(String fileName) {
        super(fileName);
    }

    @Override
    public HashSet<JobAverage> parseImpl() throws ParserImplementationException {
        final String functionName = "parseImpl()";
        try (DoubleScanner doubleScanner = new DoubleScanner(super.getFileName())) {
            doubleScanner.useDelimiter(";");
            return actualParsing(doubleScanner);
        } catch (IOException | DoubleScannerClosedException | BadFileNameException | DoubleScannerNullDelimiterException | ParseException | BadLineParseArraySizeException | InputStreamNotOpenException e) {
            throw new ParserImplementationException(this.getClass().getSimpleName(), functionName, super.getFileName(), e);
        }
    }

    private HashSet<JobAverage> actualParsing(DoubleScanner doubleScanner) throws DoubleScannerClosedException, DoubleScannerNullDelimiterException, ParseException, BadLineParseArraySizeException {
        final int amountOfColumns = EXPECTED_LINE_PARSE_ARRAY_SIZE;
        final String[] currentColumnValues = new String[amountOfColumns];
        int lineIndex = 0;
        HashSet<JobAverage> jobAverages = new HashSet<>();
        while(doubleScanner.hasNextLine()) {
            int columnIndex = 0;
            doubleScanner.nextLine();
            while(doubleScanner.hasNext()) {
                if(lineIndex != 0) {
                    currentColumnValues[columnIndex] = trimFromWhiteSpacesAndDoubleQuotes(doubleScanner.next());
                } else doubleScanner.next();
                columnIndex++;
            }
            Employee employee = getParsedEmployee(currentColumnValues, lineIndex);
            jobAverages = appendOrModifyJobAverages(jobAverages, employee);
            lineIndex++;
        }
        return jobAverages;
    }

    private HashSet<JobAverage> appendOrModifyJobAverages(HashSet<JobAverage> jobAverages, Employee employee) {
        if(employee != null) {
            if(jobAverages.contains(employee.getJob())) {
                JobAverage tmpJobAverage = null;
                for (JobAverage jobAverage : jobAverages) {
                    if (jobAverage.equals(employee.getJob())) {
                        tmpJobAverage = jobAverage;
                        break;
                    }
                }
                if(tmpJobAverage!=null) {
                    jobAverages.remove(tmpJobAverage);
                    tmpJobAverage.addAndIncrement(employee.getJob().getSalary());
                    jobAverages.add(tmpJobAverage);
                }
            } else {
                jobAverages.add(new JobAverage(employee.getJob()));
            }
        }
        return jobAverages;
    }

    private Employee getParsedEmployee(String[] currentColumnValues, int lineIndex) throws BadLineParseArraySizeException, ParseException {
        final String functionName = "getParsedEmployee(int lineIndex, String[] currentColumnValues)";
        if(currentColumnValues.length != EXPECTED_LINE_PARSE_ARRAY_SIZE)
            throw new BadLineParseArraySizeException(this.getClass().getSimpleName(), functionName, currentColumnValues.length);
        Employee employee = null;
        if(lineIndex != 0) employee = new Employee(
                currentColumnValues[0],
                currentColumnValues[1],
                currentColumnValues[2],
                currentColumnValues[3],
                currentColumnValues[4]
        );
        return employee;
    }

    private static String trimFromWhiteSpacesAndDoubleQuotes(String nextToken) {
        nextToken = nextToken.trim();
        final String doubleQuotes = "\"";
        while(nextToken.contains(doubleQuotes)) {
            final int firstIndexOfDoubleQuotes = nextToken.indexOf(doubleQuotes);
            final int lastIndexOfDoubleQuotes = nextToken.lastIndexOf(doubleQuotes);
            if(firstIndexOfDoubleQuotes == 0 && lastIndexOfDoubleQuotes == nextToken.length()-1) {
                nextToken = nextToken.substring(1, lastIndexOfDoubleQuotes);
            }
        }
        return nextToken;
    }
}
