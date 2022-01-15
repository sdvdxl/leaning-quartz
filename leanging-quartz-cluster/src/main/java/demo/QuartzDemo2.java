package demo;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@Slf4j
public class QuartzDemo2 {
  public static void main(String[] args) throws SchedulerException, InterruptedException {
    // Grab the Scheduler instance from the Factory
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    log.info("groups:{}", scheduler.getJobGroupNames());
    // and start it off
    scheduler.start();

    TimeUnit.SECONDS.sleep(6000000);

    scheduler.shutdown();
  }
}
