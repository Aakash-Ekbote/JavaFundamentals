package JavaFundamentals;

import java.time.LocalDate;

public class DateRange implements Comparable<DateRange> {
    LocalDate startDate;
    LocalDate endDate;
    public DateRange(){}

    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }



    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    @Override
    public int compareTo(DateRange obj)
    {
        if((this.startDate.compareTo(obj.startDate))==0)
        {
            return this.endDate.compareTo(obj.endDate);
        }
        return this.startDate.compareTo(obj.startDate);
    }
}
