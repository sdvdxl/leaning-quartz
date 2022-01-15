package top.todu.leaning.quartz.demo;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class HelloJob implements org.quartz.Job {

  @Override
  public void execute(JobExecutionContext ctx) throws JobExecutionException {

    log.info("执行自定义任务 name {}", ctx.getTrigger().getJobDataMap().get("name"));
  }
}
