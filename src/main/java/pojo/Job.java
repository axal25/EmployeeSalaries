package pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Getter
@Setter
public class Job {
    public static final String DEFAULT_CURRENCY_STRING = "PLN";
    public static final CurrencyUnit DEFAULT_CURRENCY_UNIT = Monetary.getCurrency(DEFAULT_CURRENCY_STRING);
    public static final Locale DEFAULT_LOCALE = Locale.GERMANY;
    public static final String DEFAULT_DECIMAL_SEPARATOR = ",";
    public static final String[] INVALID_DECIMAL_SEPARATORS = {"."};

    private String jobTitle;
    @JsonSerialize(using = MoneySerializer.class)
    private Money salary;

    @JsonCreator
    public Job(
            @JsonProperty("job") String jobTitle,
            @JsonProperty("salary") String salary
    ) throws ParseException {
        this.jobTitle = jobTitle;
        this.salary = Money.of(parseToBigDecimal(salary), DEFAULT_CURRENCY_UNIT);
    }

    protected Job(String jobTitle, Money salary) {
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    private BigDecimal parseToBigDecimal(String salary) throws ParseException {
        salary = replacePotentialInvalidDecimalSeparators(salary);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(DEFAULT_LOCALE);
        ((DecimalFormat) numberFormat).setParseBigDecimal(true);
        return (BigDecimal) numberFormat.parse(salary);
    }

    private String replacePotentialInvalidDecimalSeparators(String salary) {
        for (String invalidDecimalSeparator : INVALID_DECIMAL_SEPARATORS) {
            if(
                    salary.contains(invalidDecimalSeparator) &&
                    salary.indexOf(invalidDecimalSeparator) == salary.lastIndexOf(invalidDecimalSeparator)
            ) salary = salary.replace(invalidDecimalSeparator, DEFAULT_DECIMAL_SEPARATOR);
        }
        return salary;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Job{")
                .append("jobTitle='")
                .append(jobTitle)
                .append('\'')
                .append(", salary=")
                .append(salary)
                .append('}')
                .toString();
    }

    @Override
    public boolean equals(Object unknownTypeOther) {
        if (this == unknownTypeOther) return true;
        if (!(unknownTypeOther instanceof Job)) return false;
        Job other = (Job) unknownTypeOther;
        return this.getJobTitle().compareTo(other.getJobTitle()) == 0;
//        return getJobTitle().equals(other.getJobTitle()) &&
//                getSalary().equals(other.getSalary());
    }

    @Override
    public int hashCode() {
        return getJobTitle().hashCode();
//        return Objects.hash(getJobTitle(), getSalary());
    }
}
