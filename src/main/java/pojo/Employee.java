package pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;

@Getter
@Setter
public class Employee {

    int id;
    String name, surname;
    /** Can NOT mix @JsonUnwrapped and @JsonCreator... @JsonProperty...
     * The workaround is to provide NoArgsConstructor and Setters
     * https://github.com/FasterXML/jackson-module-kotlin/issues/106
     * https://github.com/FasterXML/jackson-databind/issues/1497
     * https://github.com/FasterXML/jackson-databind/issues/1467
     */
    @JsonUnwrapped
    Job job;

    public Employee() {
        this.id = Integer.MIN_VALUE;
        this.name = null;
        this.surname = null;
        this.job = null;
    }

    public Employee(
            String id,
            String name,
            String surname,
            String jobTitle,
            String salary) throws ParseException {
        this.id = Integer.parseInt(id);
        this.name = name;
        this.surname = surname;
        this.job = new Job(jobTitle, salary);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Employee{")
                .append("id=")
                .append(id)
                .append(", name='")
                .append(name)
                .append('\'')
                .append(", surname='")
                .append(surname)
                .append('\'')
                .append(", job=")
                .append(job)
                .append('}')
                .toString();
    }
}
