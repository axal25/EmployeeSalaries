package main;

import file.ops.exceptions.BadFileNameException;
import file.ops.parsers.FileParser;
import file.ops.parsers.FileParserFactory;
import file.ops.parsers.exeptions.BadFileExtensionException;
import file.ops.parsers.impl.exceptions.ParserImplementationException;
import pojo.JobAverage;

import java.io.File;
import java.util.HashSet;

public class Main {

    public static final String[] EXISTING_CSV_RESOURCE_RELATIVE_PATHS = {
            new StringBuilder().append(File.separator).append("csv").append(File.separator).append("employees.csv").toString(),
            new StringBuilder().append(File.separator).append("csv").append(File.separator).append("employees with spaces.csv").toString(),
            new StringBuilder().append(File.separator).append("test directory with spaces").append(File.separator).append("employees.csv").toString(),
            new StringBuilder().append(File.separator).append("test directory with spaces").append(File.separator).append("employees with spaces.csv").toString(),
            new StringBuilder().append(File.separator).append("csv").append(File.separator).append("employees with 3 same job titles or more.csv").toString(),
            new StringBuilder().append(File.separator).append("test directory with spaces").append(File.separator).append("employees with 3 same job titles or more.csv").toString(),
    };

    public static final String[] EXISTING_JSON_RESOURCE_RELATIVE_PATHS = {
            new StringBuilder().append(File.separator).append("json").append(File.separator).append("employees.json").toString(),
            new StringBuilder().append(File.separator).append("json").append(File.separator).append("employees with spaces.json").toString(),
            new StringBuilder().append(File.separator).append("test directory with spaces").append(File.separator).append("employees.json").toString(),
            new StringBuilder().append(File.separator).append("test directory with spaces").append(File.separator).append("employees with spaces.json").toString(),
            new StringBuilder().append(File.separator).append("json").append(File.separator).append("employees with 3 same job titles or more.json").toString(),
            new StringBuilder().append(File.separator).append("test directory with spaces").append(File.separator).append("employees with 3 same job titles or more.json").toString(),
    };
    public static final int DIFFERENT_CONTENTS_INDEX = 4;

    public static void main(String[] args) {
        System.out.println(getOutputMessage(args));
    }

    public static String getOutputMessage(String[] filePaths) {
        StringBuilder outputMessage = new StringBuilder();
        if(filePaths != null && filePaths.length > 0) {
            for (String filePath : filePaths) {
                outputMessage.append("\n\rFile: ").append(filePath).append("\n\r");
                try {
                    FileParser fileParser = FileParserFactory.getFileParser(filePath);
                    HashSet<JobAverage> jobAverages = fileParser.parse(filePath);
                    outputMessage.append(getOutputMessage(jobAverages));
                } catch(BadFileExtensionException | BadFileNameException | ParserImplementationException e) {
                    outputMessage.append(e.getMessage()).append("\n\r");
                }
            }
        }
        else {
            outputMessage.append("None filePaths were given. You need to pass path to the source file. For example: [\n\r");
            for(String existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
                outputMessage.append("\t").append(existingResourceRelativePath).append(",\n\r");
            }
            outputMessage.append("]\n\rOr if the source file is in the same directory as jar just the file name.");
        }
        return outputMessage.toString();
    }

    public static String getOutputMessage(HashSet<JobAverage> jobAverages) {
        StringBuilder outputMessage = new StringBuilder();
        jobAverages.forEach(jobAverage -> {
            outputMessage
                    .append("All ")
                    .append(jobAverage.getJobTitle())
                    .append("(s) together make ")
                    .append(jobAverage.getSalary())
                    .append(" but every ")
                    .append(jobAverage.getJobTitle())
                    .append(" makes on average ")
                    .append(jobAverage.getAverage())
                    .append(". Based on ")
                    .append(jobAverage.getAmountOfEntries())
                    .append(" entry/entries.")
                    .append("\n\r");
        });
        return outputMessage.toString();
    }
}
