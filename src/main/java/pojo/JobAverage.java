package pojo;

import lombok.Getter;
import org.javamoney.moneta.Money;

import java.text.ParseException;

@Getter
public class JobAverage extends Job {
    int amountOfEntries = 0;

    public JobAverage(Job job) {
        super(job.getJobTitle(), job.getSalary());
        this.amountOfEntries++;
    }

    private JobAverage(String jobTitle, String salary) throws ParseException {
        super(jobTitle, salary);
    }

    public void addAndIncrement(Money salary) {
        super.setSalary(super.getSalary().add(salary));
        incrementAmountOfEntries();
    }

    public void incrementAmountOfEntries() {
        this.amountOfEntries++;
    }

    public Money getAverage() {
        return super.getSalary().divide(amountOfEntries);
    }

    @Override
    public boolean equals(Object unknownTypeOther) {
        if (this == unknownTypeOther) return true;
        if (!(unknownTypeOther instanceof Job)) return false;
        Job other = (Job) unknownTypeOther;
        return this.getJobTitle().compareTo(other.getJobTitle()) == 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
//        return Objects.hash(super.hashCode(), amountOfEntries);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("JobAverage{")
                .append("amountOfEntries=")
                .append(amountOfEntries)
                .append(", job=")
                .append(super.toString())
                .append("}")
                .toString();
    }
}
