package pojo;

import main.MainTest;
import org.junit.jupiter.api.*;

import java.text.ParseException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTest {
    public final static String[][] EMPLOYEES = {
            {"1", "Mark", "Green", "Teacher", "3540,20"},
            {"2", "Oscar", "Mustache", "Janitor", "13460.45"},
            {"3", "Adalbert", "Kidney", "Priest", "15240.00"},
            {"4", "Christopher", "Głuś", "Teacher", "2700,10"},
            {"5", "Michael", "Spear", "Janitor", "13460,45"},
    };

    @Test
    @Order(1)
    @DisplayName("Employee constructor")
    void constructor() throws ParseException {
        for(String[] employeeData : EMPLOYEES) {
            Employee employee = new Employee(
                    employeeData[0],
                    employeeData[1],
                    employeeData[2],
                    employeeData[3],
                    employeeData[4]
            );
            if(MainTest.DO_PRINT_TEST_DATA_TO_SYSTEM_OUT) System.out.println(employee);
        }
    }
}
