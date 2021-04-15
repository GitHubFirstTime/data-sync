package com.rlc.cmdb.dataSync.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

/**
 * @author rlc_zyc
 * @version 1.0
 * @description: TODO
 * @date 2021/4/12 10:02
 */
public class CustomGlobalJobListener extends JobListenerSupport {
    @Override
    public String getName() {
        return this.getClass().getName();
    }
    /**
     * Scheduler 在 JobDetail 将要被执行时调用这个方法。
     *
     * @param context
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        getLog().debug("计划 {} ： ~~~ 【RUNNING】 更新正在运行中状态 ~~~ ",context.getJobDetail().getJobClass().getName());
    }
    /**
     * Scheduler 在 JobDetail 即将被执行，但又被 TriggerListener 否决了时调用这个方法
     *
     * @param context
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
    }
    /**
     * Scheduler 在 JobDetail 被执行之后调用这个方法
     *
     * @param context
     * @param jobException
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        getLog().debug("计划 {} ： ~~~ 【COMPLETE | ERROR】 更新已经结束状态 ~~~ ");
        // 唤醒子任务
//        batchScheduleService.notifyChildren(scheduleJob);
    }
}
