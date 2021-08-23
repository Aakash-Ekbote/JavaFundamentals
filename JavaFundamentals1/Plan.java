package JavaFundamentals1;

import JavaFundamentals.DateRange;

import java.time.LocalDate;

public class Plan implements Comparable<Plan>{
    int taskId;
    LocalDate startDate;
    LocalDate endDate;

    public Plan(){}

    public Plan(int taskId, LocalDate startDate, LocalDate endDate) {
        this.taskId = taskId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "taskId=" + taskId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
    public int compareTo(Plan obj)
    {
        if((this.startDate.compareTo(obj.startDate))==0)
        {
            return this.endDate.compareTo(obj.endDate);
        }
        return this.startDate.compareTo(obj.startDate);
    }
}
