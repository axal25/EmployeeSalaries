package pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.MainTest;
import org.junit.jupiter.api.*;
import pojo.Employee;

import java.text.ParseException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JacksonTest {
    public final static String[][] EMPLOYEES = EmployeeTest.EMPLOYEES;

    @Test
    @Order(1)
    @DisplayName("ObjectMapper writeValue Employee")
    void objectMapperWriteEmployee() throws JsonProcessingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        for(String[] employeeData : EMPLOYEES) {
            Employee employee = new Employee(
                    employeeData[0],
                    employeeData[1],
                    employeeData[2],
                    employeeData[3],
                    employeeData[4]
            );
            if(MainTest.DO_PRINT_TEST_DATA_TO_SYSTEM_OUT) System.out.println(employee);
            String employeeString = objectMapper.writeValueAsString(employee);
            if(MainTest.DO_PRINT_TEST_DATA_TO_SYSTEM_OUT) System.out.println(employeeString);
        }
    }

    @Test
    @Order(2)
    @DisplayName("ObjectMapper readValue written Employee")
    void objectMapperReadWrittenEmployee() throws JsonProcessingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < EMPLOYEES.length; i++) {
            Employee employee = new Employee(
                    EMPLOYEES[i][0],
                    EMPLOYEES[i][1],
                    EMPLOYEES[i][2],
                    EMPLOYEES[i][3],
                    EMPLOYEES[i][4]
            );
            if(MainTest.DO_PRINT_TEST_DATA_TO_SYSTEM_OUT) System.out.println(employee);
            String employeeString = objectMapper.writeValueAsString(employee);
            if(MainTest.DO_PRINT_TEST_DATA_TO_SYSTEM_OUT) System.out.println(employeeString);
            employee = objectMapper.readValue(employeeString, Employee.class);
        }
    }
}
