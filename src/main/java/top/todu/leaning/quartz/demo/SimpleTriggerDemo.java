package top.todu.leaning.quartz.demo;

import java.util.Date;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;

public class SimpleTriggerDemo {
  public static void main(String[] args) {
    SimpleTrigger trigger =
        (SimpleTrigger)
            TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                // some Date
                .startAt(DateBuilder.futureDate(5, IntervalUnit.MINUTE))
                // identify job with name, group strings
                .forJob("job1", "group1").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();

    System.out.println(trigger.getRepeatCount());
  }
}
