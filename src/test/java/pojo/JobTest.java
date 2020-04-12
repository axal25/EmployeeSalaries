package pojo;

import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JobTest {
    public static final String[][] DEFAULT_JOBS = {
            {"Teacher", "3540,20"},
            {"Janitor", "13460.45"},
            {"Priest", "15240.00"},
            {"Teacher", "2700,10"},
            {"Janitor", "13460,45"},
    };

    @Test
    @Order(1)
    @DisplayName("Job constructor")
    void constructor() throws ParseException {
        HashSet<Job> jobs = new HashSet<>();
        for(String[] jobData : DEFAULT_JOBS) {
            jobs.add(
                    new Job(
                            jobData[0],
                            jobData[1]
                    )
            );
        }
    }

    @Test
    @Order(2)
    @DisplayName("Job equals & hashCode")
    void equalsAndHashCode() throws ParseException {
        HashSet<Job> jobs = new HashSet<>();
        for(String[] jobData : DEFAULT_JOBS) {
            jobs.add(
                    new Job(
                            jobData[0],
                            jobData[1]
                    )
            );
        }
        int i = 0;
        for(Job job : jobs) {
            assertTrue(jobs.contains(job));
            assertTrue(jobs.contains(
                    new Job(
                            DEFAULT_JOBS[i][0],
                            DEFAULT_JOBS[i][1]
                    )
            ));
            i++;
        }
    }
}
