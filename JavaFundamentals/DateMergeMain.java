package JavaFundamentals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateMergeMain {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of inputs");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter inputs");
        ArrayList<DateRange> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] inputs = sc.nextLine().split(" ");
            String startDate = inputs[0];
            String endDate = inputs[1];
            //System.out.println(startDate+" "+endDate);
            LocalDate sd = LocalDate.parse(startDate, formatter);
            LocalDate ed = LocalDate.parse(endDate, formatter);
            DateRange range=new DateRange(sd,ed);
            intervals.add(range);
        }
        System.out.println(intervals);
        DateMerger obj=new DateMerger();
        List<DateRange> res=obj.mergeDateRange(intervals);
        for(DateRange x:res)
        {
            System.out.println(formatter.format(x.startDate)+"  "+formatter.format(x.endDate));
        }

    }

    }
