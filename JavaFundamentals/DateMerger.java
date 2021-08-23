package JavaFundamentals;

import java.time.LocalDate;
import java.util.*;

public class DateMerger {
    public List<DateRange> mergeDateRange(List<DateRange> dateRanges)
    {
        List<DateRange> res=new ArrayList<>();
        LocalDate start=dateRanges.get(0).getStartDate();
        LocalDate end=dateRanges.get(0).getEndDate();
        for(DateRange obj:dateRanges)
        {
            if((obj.getStartDate().compareTo(end))<0)
            {
                end=(end.compareTo(obj.getEndDate())<0)?obj.getEndDate():end;
            }
            else
            {
                res.add(new DateRange(start,end));
                start=obj.startDate;
                end=obj.endDate;
            }
        }
        res.add(new DateRange(start,end));
        return  res;
    }
}
