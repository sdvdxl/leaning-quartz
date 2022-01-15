package top.todu.leaning.quartz.demo;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

@Slf4j
public class QuartzDemo {
  public static void main(String[] args) throws SchedulerException, InterruptedException {
    // Grab the Scheduler instance from the Factory
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

    // and start it off
    scheduler.start();

    JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
    // 排除某些时间
    HolidayCalendar cal = new HolidayCalendar();
    cal.addExcludedDate(
        new java.util.Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10)));
    scheduler.addCalendar("a", cal, true, true);

    // Trigger the job to run now, and then repeat every 40 seconds
    Trigger trigger =
        TriggerBuilder.newTrigger()
            .withIdentity("trigger1", "group1")
            .startNow()
            .usingJobData("name", "todu")
            .modifiedByCalendar("a")
            .withSchedule(
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
            .build();

    scheduler.scheduleJob(job, trigger);
    TimeUnit.SECONDS.sleep(60);

    scheduler.shutdown();
  }
}
