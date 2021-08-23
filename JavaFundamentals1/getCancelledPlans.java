package JavaFundamentals1;

import java.lang.reflect.Array;
import java.util.*;


class SortResult implements Comparator<Plan>
{
    @Override
    public int compare(Plan o1,Plan o2)
    {
        if(o1.taskId==o2.taskId)
        {
            if(o1.startDate.compareTo(o2.startDate)==0)
            {
                return o1.endDate.compareTo(o2.endDate);
            }
            return o1.startDate.compareTo(o2.endDate);
        }
        return o1.taskId- o2.taskId;
    }
}

public class getCancelledPlans {

    private HashMap<Integer,ArrayList<Plan>> getPlan(List<Plan> al)
    {
        HashMap<Integer,ArrayList<Plan>> hm=new HashMap<>();
        for(Plan plan:al)
        {
            ArrayList<Plan> temp;
            if(hm.containsKey(plan.taskId))
            {
               temp=hm.get(plan.taskId);
            }
            else
            {
             temp=new ArrayList<>();
            }
            temp.add(plan);
            hm.put(plan.taskId,temp);
        }
        return hm;
    }
    private void updateandremove(List<Plan> initial,Plan newPlan)
    {
        //sort the days by start date
        Collections.sort(initial);
        int i = 0;
        //If new plan ends before start of old plan then old plan is only unused plan
        if(newPlan.endDate.compareTo(initial.get(0).startDate) < 0)
            return;

        //ignoring the non-overlapping old plans as they contribute to cancelled plans
        while(i < initial.size() && initial.get(i).endDate.compareTo(newPlan.startDate)<0)
            i++;
        //reached end, don't have to check for remaining conditions
        if(i == initial.size())
            return;

        //new plan starts before the old plan ,,, old : [103 10-May-2019 20-May-2019] new: [103 05-May-2019 25-May-2019] covering full
        if(newPlan.startDate.compareTo(initial.get(i).startDate) <= 0 )
        {
            //New plan covers old plan then old plan is utilized fully
            if(newPlan.endDate.compareTo(initial.get(i).endDate) >= 0 )
                initial.remove(i);
            // new plan doesn't cover fully example : old :[102 10-May-2019 20-May-2019] new : [102 05-May-2019 15-May-2019]
            else {
                initial.get(i).startDate=(newPlan.endDate.plusDays(1));
            }
            return;
        }

        //old plan covers new plan Example :old : [104 10-May-2019 20-May-2019] new: [104 15-May-2019 18-May-2019]
        else if(newPlan.startDate.compareTo(initial.get(i).startDate) > 0 &&  newPlan.endDate.compareTo(initial.get(i).endDate) < 0)
        {
            // o/p: [19-May-2019 to 20-May-2019]
            Plan unused_date = new Plan();
            unused_date.startDate=(newPlan.endDate.plusDays(1));
            unused_date.endDate=initial.get(i).endDate;
            unused_date.taskId=initial.get(i).taskId;
            initial.add(unused_date);
            // [10-May-2019 to 14-May-2019]
            initial.get(i).endDate=(newPlan.startDate.minusDays(1));
            return;
        }

        //new plan starts before end of old plan example : old : [105 10-May-2019 20-May-2019] new : [105 15-May-2019 25-May-2019]
        //o/p [10-May-2019 to 14-May-2019]
        else
        {
            initial.get(i).endDate=(newPlan.startDate.minusDays(1));
           return;
        }


    }
    public List<Plan> getCancelledPeriodsForTask(List<Plan> oldPlanList, List<Plan> newPlanList)
    {
        List<Plan> res=new ArrayList<>();
        HashMap<Integer,ArrayList<Plan>> oldhm=getPlan(oldPlanList);
        HashMap<Integer,ArrayList<Plan>> newhm=getPlan(newPlanList);
        for(int tid:oldhm.keySet())
        {
            List<Plan> initial=new ArrayList<>(oldhm.get(tid));
            for(Plan newplan:newhm.get(tid))
            {
                updateandremove(initial,newplan);
            }
            res.addAll(initial);

        }
        Collections.sort(res,new SortResult());
        return res;


    }
}
