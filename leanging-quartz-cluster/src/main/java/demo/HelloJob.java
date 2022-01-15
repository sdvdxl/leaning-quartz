package demo;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class HelloJob implements org.quartz.Job {

  @Override
  public void execute(JobExecutionContext ctx) throws JobExecutionException {
    ctx.getMergedJobDataMap().put("ss",System.currentTimeMillis());
    log.info("ctxMap: {}",ctx.getMergedJobDataMap().get("ss"));
    log.info("执行自定义任务 name {}", ctx.getTrigger().getJobDataMap().get("name"));
  }
}
