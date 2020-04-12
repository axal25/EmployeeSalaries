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

    public static final File EXISTING_CSV_RESOURCE_DIRECTORY_RELATIVE_PATHS = new File(File.separator + "csv");
    public static final File EXISTING_JSON_RESOURCE_DIRECTORY_RELATIVE_PATHS = new File(File.separator + "json");
    public static final File EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS = new File(File.separator + "test directory with spaces");

    public static final File[] EXISTING_CSV_RESOURCE_RELATIVE_PATHS = {
            new File(EXISTING_CSV_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees.csv"),
            new File(EXISTING_CSV_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with spaces.csv"),
            new File(EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees.csv"),
            new File(EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with spaces.csv"),
            new File(EXISTING_CSV_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with 3 same job titles or more.csv"),
            new File(EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with 3 same job titles or more.csv"),
    };

    public static final File[] EXISTING_JSON_RESOURCE_RELATIVE_PATHS = new File[]{
            new File(EXISTING_JSON_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees.json"),
            new File(EXISTING_JSON_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with spaces.json"),
            new File(EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees.json"),
            new File(EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with spaces.json"),
            new File(EXISTING_JSON_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with 3 same job titles or more.json"),
            new File(EXISTING_TEST_RESOURCE_DIRECTORY_RELATIVE_PATHS, "employees with 3 same job titles or more.json"),
    };

    public static final int DIFFERENT_CONTENTS_INDEX = 4; // At which index files have different contents

    public static void main(String[] args) {
        System.out.println(getOutputMessage(args));
    }

    public static String getOutputMessage(String[] filePaths) {
        final String functionName = "getOutputMessage(String[] filePaths)";
        StringBuilder outputMessage = new StringBuilder();
        if(filePaths != null && filePaths.length > 0) {
            for (String filePath : filePaths) {
                outputMessage.append("\n\rFile: ").append(filePath).append("\n\r");
                try {
                    if(filePath == null) throw new BadFileNameException(Main.class.getSimpleName(), functionName, filePath, null);
                    File file = new File(filePath);
                    FileParser fileParser = FileParserFactory.getFileParser(file);
                    HashSet<JobAverage> jobAverages = fileParser.parse(file);
                    outputMessage.append(getOutputMessage(jobAverages));
                } catch(BadFileExtensionException | BadFileNameException | ParserImplementationException e) {
                    outputMessage.append(e.getMessage()).append("\n\r");
                }
            }
        }
        else {
            outputMessage.append("None filePaths were given. You need to pass path to the source file. For example: [\n\r");
            for(File existingResourceRelativePath : EXISTING_CSV_RESOURCE_RELATIVE_PATHS) {
                outputMessage.append("\t\"").append(existingResourceRelativePath.getPath()).append("\",\n\r");
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
