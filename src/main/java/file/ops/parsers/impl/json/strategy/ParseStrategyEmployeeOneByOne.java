package file.ops.parsers.impl.json.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import file.ops.InputStreamOps;
import file.ops.exceptions.*;
import file.ops.parsers.impl.exceptions.ParserImplementationException;
import pojo.Employee;
import pojo.JobAverage;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class ParseStrategyEmployeeOneByOne implements IParse {
    public static final String EMPLOYEES_ARRAY_FIELD_NAME = "employees";

    @Override
    public HashSet<JobAverage> parse(File file) throws ParserImplementationException {
        return employeesParsing(file);
    }

    private HashSet<JobAverage> employeesParsing(File file) throws ParserImplementationException {
        final String functionName = "actualParsing(String contentsOfEmployeesArray, String fileName)";
        try(Scanner scanner = new Scanner(InputStreamOps.getNewInputStream(file))) {
            scanner.useDelimiter("(?<=\\})[\\s\n\r]*,[\\s\n\r]*(?=\\{)");
            HashSet<JobAverage> jobAverages = new HashSet<>();
            ObjectMapper objectMapper = new ObjectMapper();
            if(scanner.hasNext()) {
                String wholeEmployee = scanner.next();
                wholeEmployee = trimFirstEmployee(wholeEmployee);
                if(!scanner.hasNext()) wholeEmployee = trimLastEmployee(wholeEmployee);
                jobAverages = addEmployeesJobToJobAverages(jobAverages, objectMapper.readValue(wholeEmployee, Employee.class));
            }
            while(scanner.hasNext()) {
                String wholeEmployee = scanner.next();
                if(!scanner.hasNext()) wholeEmployee = trimLastEmployee(wholeEmployee);
                jobAverages = addEmployeesJobToJobAverages(jobAverages, objectMapper.readValue(wholeEmployee, Employee.class));
            }
            return jobAverages;
        } catch (InputStreamNotOpenException | IOException | BadFileNameException e) {
            throw new ParserImplementationException(this.getClass().getSimpleName(), functionName, file.getPath(), e);
        }
    }

    private HashSet<JobAverage> addEmployeesJobToJobAverages(HashSet<JobAverage> jobAverages, Employee employee) {
        if(jobAverages.contains(employee.getJob())) {
            jobAverages.forEach(jobAverage -> {
                if(jobAverage.equals(employee.getJob())) jobAverage.addAndIncrement(employee.getJob().getSalary());
            });
        } else jobAverages.add(new JobAverage(employee.getJob()));
        return jobAverages;
    }

    private String trimFirstEmployee(String wholeEmployee) {
        final String regexFragmentToRemove = new StringBuilder()
                .append("\\{")
                .append("[\\s\\S]*")
                .append("\\\"")
                .append(EMPLOYEES_ARRAY_FIELD_NAME)
                .append("\\\"")
                .append("[\\s\\S]*")
                .append(":")
                .append("[\\s\\S]*")
                .append("\\[")
                .toString();
        if(wholeEmployee.matches(
                new StringBuilder()
                        .append(regexFragmentToRemove)
                        .append("[\\s\\S]*")
                        .toString()
        )) {
            return wholeEmployee.replaceFirst(
                    regexFragmentToRemove,
                    ""
            );
        }
        return wholeEmployee;
    }

    private String trimLastEmployee(String wholeEmployee) {
        final String regexFragmentToRemove = new StringBuilder()
                .append("(?<=[\\s\\S]*)")
                .append("\\]")
                .append("[\\s\\S]*")
                .append("\\}")
                .toString();
        if(wholeEmployee.matches(regexFragmentToRemove)) {
            return wholeEmployee.replace(
                    regexFragmentToRemove,
                    ""
            );
        }
        return wholeEmployee;
    }
}
