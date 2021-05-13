package com.ruoyi.quartz.util;

import com.ruoyi.quartz.domain.Job;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 *
 * @author ruoyi
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Job sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
