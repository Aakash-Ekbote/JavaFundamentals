package JavaFundamentals1;

import JavaFundamentals.DateRange;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlanMain {
    public static void main(String args[]) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        System.out.println("Enter no of old plans");
        int n1=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the old plan details");
        List<Plan> oldplan=new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            String[] inputs = sc.nextLine().split(" ");
            int id=Integer.parseInt(inputs[0]);
            String startDate = inputs[1];

            String endDate = inputs[2];

            //System.out.println(startDate+" "+endDate+" "+id);

            LocalDate sd = LocalDate.parse(startDate, formatter);
            LocalDate ed = LocalDate.parse(endDate, formatter);
            Plan plan=new Plan(id,sd,ed);
            oldplan.add(plan);
        }
        System.out.println("Enter no of new plans");
        int n2=sc.nextInt();
        sc.nextLine();
        List<Plan> newplan=new ArrayList<>();
        System.out.println("Enter new plan details");
        for(int i=0;i<n2;i++)
        {
            String[] inputs = sc.nextLine().split(" ");
            int id=Integer.parseInt(inputs[0]);
            String startDate = inputs[1];
            String endDate = inputs[2];

            //System.out.println(startDate+" "+endDate);
            LocalDate sd = LocalDate.parse(startDate, formatter);
            LocalDate ed = LocalDate.parse(endDate, formatter);
            Plan plan=new Plan(id,sd,ed);
            newplan.add(plan);
        }
        getCancelledPlans obj=new getCancelledPlans();
        List<Plan> res=obj.getCancelledPeriodsForTask(oldplan,newplan);
        System.out.println("TASKID"+" "+"START DATE"+" "+"END DATE");
        for(Plan plan: res)
        {
            System.out.println(plan.getTaskId()+"  " +
                    formatter.format(plan.getStartDate()) + "  " +
                    formatter.format(plan.getEndDate()));
        }

    }
}
