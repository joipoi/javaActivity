import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Activity implements Serializable {
    String name;
    LocalDate lastDate;

    public Activity(String name) {
        this.name = name;
        lastDate = LocalDate.now();

    }

    @Override
    public String toString() {



        long numOfDays = ChronoUnit.DAYS.between(lastDate, LocalDateTime.now());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy,");
        return name + " " + numOfDays ; //lastDate.format(formatter)

    }
}
