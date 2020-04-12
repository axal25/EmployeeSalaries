package file.ops.parsers.impl.json.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import file.ops.InputStreamOps;
import file.ops.exceptions.BadFileNameException;
import file.ops.exceptions.InputStreamNotOpenException;
import file.ops.parsers.impl.exceptions.ParserImplementationException;
import pojo.Employee;
import pojo.EmployeeArray;
import pojo.JobAverage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;

public class ParseStrategyEmployeeArrayAsWhole implements IParse {

    @Override
    public HashSet<JobAverage> parse(String fileName) throws ParserImplementationException {
        final String functionName = "parse(String fileName)";
        try (InputStream inputStream = InputStreamOps.getNewInputStream(fileName)) {
            return actualParsing(inputStream);
        } catch (IOException | BadFileNameException | InputStreamNotOpenException e) {
            throw new ParserImplementationException(this.getClass().getSimpleName(), functionName, fileName, e);
        }
    }

    private HashSet<JobAverage> actualParsing(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeArray employeeArray = objectMapper.readValue(inputStream, EmployeeArray.class);
        Employee[] employees = employeeArray.getEmployees();
        HashSet<JobAverage> jobAverages = new HashSet<>();
        Arrays.asList(employees).forEach(employee -> {
            if(jobAverages.contains(employee.getJob())) {
                jobAverages.forEach(jobAverage -> {
                    if(jobAverage.equals(employee.getJob())) jobAverage.addAndIncrement(employee.getJob().getSalary());
                });
            } else jobAverages.add(new JobAverage(employee.getJob()));
        });
        return jobAverages;
    }
}
