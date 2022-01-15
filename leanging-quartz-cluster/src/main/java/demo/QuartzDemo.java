package demo;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
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
    scheduler.deleteJob(JobKey.jobKey("job1", "group1"));
    // 如果时间比较短，比如2s以下，可能会多实例重复运行同一个任务
//    TimeUnit.SECONDS.sleep(1);
    JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
    // Trigger the job to run now, and then repeat every 40 seconds
    Trigger trigger =
        TriggerBuilder.newTrigger()
            .withIdentity("trigger", "group")
            .startNow()
            .usingJobData("name", "todu")
            //            .modifiedByCalendar("a")
            .withSchedule(
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
            .build();

    scheduler.scheduleJob(job, trigger);
    TimeUnit.SECONDS.sleep(600000);

    scheduler.shutdown();
  }
}
