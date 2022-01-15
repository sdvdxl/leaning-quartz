package top.todu.leaning.quartz.demo;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.OrMatcher;
import org.quartz.listeners.JobListenerSupport;
import org.quartz.listeners.TriggerListenerSupport;

public class JobListenerDemo {
  public static void main(String[] args) throws SchedulerException {
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler
        .getListenerManager()
        .addTriggerListener(
            new TriggerListenerSupport() {
              @Override
              public String getName() {
                return null;
              }
            });

    scheduler
        .getListenerManager()
        .addJobListener(
            new JobListenerSupport() {
              @Override
              public String getName() {
                return null;
              }
            },
            OrMatcher.or(GroupMatcher.jobGroupEquals("group1"), GroupMatcher.jobGroupEquals("group2")));
  }
}
