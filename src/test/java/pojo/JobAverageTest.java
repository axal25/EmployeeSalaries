package pojo;

import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JobAverageTest {
    public static final String[][] DEFAULT_JOBS = JobTest.DEFAULT_JOBS;

    @Test
    @Order(1)
    @DisplayName("JobAverage constructor")
    void constructor() throws ParseException {
        HashSet<JobAverage> jobAverages = new HashSet<>();
        for(String[] jobData : DEFAULT_JOBS) {
            jobAverages.add(
                    new JobAverage(
                            new Job(
                                    jobData[0],
                                    jobData[1]
                            )
                    )
            );
        }
    }

    @Test
    @Order(2)
    @DisplayName("JobAverage equals & hashCode")
    void equalsAndHashCode() throws ParseException {
        HashSet<JobAverage> jobAverages = new HashSet<>();
        for(String[] jobData : DEFAULT_JOBS) {
            jobAverages.add(
                    new JobAverage(
                            new Job(
                                    jobData[0],
                                    jobData[1]
                            )
                    )
            );
        }
        int i = 0;
        for(Job jobAverage : jobAverages) {
            assertTrue(jobAverages.contains(jobAverage));
            assertTrue(jobAverages.contains(
                    new JobAverage(
                            new Job(
                                    DEFAULT_JOBS[i][0],
                                    DEFAULT_JOBS[i][1]
                            )
                    )
            ));
            i++;
        }
    }

    @Test
    @Order(3)
    @DisplayName("JobAverage equals & hashCode Job")
    void equalsAndHashCodeJob() throws ParseException {
        HashSet<JobAverage> jobAverages = new HashSet<>();
        for(String[] jobData : DEFAULT_JOBS) {
            jobAverages.add(
                    new JobAverage(
                            new Job(
                                    jobData[0],
                                    jobData[1]
                            )
                    )
            );
        }
        int i = 0;
        for(Job jobAverage : jobAverages) {
            assertTrue(jobAverages.contains(jobAverage));
            assertTrue(jobAverages.contains(
                    new Job(
                            DEFAULT_JOBS[i][0],
                            DEFAULT_JOBS[i][1]
                    )
            ));
            i++;
        }
    }

    @Test
    @Order(4)
    @DisplayName("JobAverage equals & hashCode Job (2)")
    void equalsAndHashCodeJob2() throws ParseException {
        JobAverage[] jobAverages = new JobAverage[DEFAULT_JOBS.length];
        Job[] jobs = new Job[DEFAULT_JOBS.length];
        for (int i = 0; i < DEFAULT_JOBS.length; i++) {
            jobs[i] = new Job(
                    DEFAULT_JOBS[i][0],
                    DEFAULT_JOBS[i][1]
            );
            jobAverages[i] = new JobAverage(jobs[i]);
            assertTrue(jobAverages[i].equals(jobs[i]));
            assertTrue(jobs[i].equals(jobAverages[i]));
            assertTrue(Objects.equals(jobAverages[i], jobs[i]));
            assertTrue(Objects.equals(jobs[i], jobAverages[i]));
            // javadocs are misleading https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/HashSet.html
            assertTrue(jobs[i].hashCode() == jobAverages[i].hashCode());
            assertEquals(jobs[i].hashCode(), jobAverages[i].hashCode());
        }
    }
}
