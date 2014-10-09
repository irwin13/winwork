package com.irwin13.winwork.basic.scheduler;

import com.google.inject.Inject;
import com.irwin13.winwork.basic.annotations.MDCLog;
import com.irwin13.winwork.basic.exception.WinWorkException;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

/**
 * @author irwin Timestamp : 12/05/2014 17:13
 */
public class BasicSchedulerManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicSchedulerManager.class);
    private final Scheduler scheduler;

    @Inject
    public BasicSchedulerManager(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * Start the the id.co.quadras.siak.konsolidasi.scheduler engine
     * @throws org.quartz.SchedulerException
     */
    public void start() throws SchedulerException {
        scheduler.start();
    }

    /**
     * Shutdown the id.co.quadras.siak.konsolidasi.scheduler engine
     * @param waitJobsToComplete if true will wait all the jobs to complete before shutdown
     * @throws org.quartz.SchedulerException
     */
    public void shutdown(boolean waitJobsToComplete) throws SchedulerException {
        scheduler.shutdown(waitJobsToComplete);
    }

    /**
     * Add new schedule to run
     * @param jobDetail job to be run
     * @param trigger trigger date when the schedule will run
     * @throws org.quartz.SchedulerException
     */
    public void add(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * Remove schedule from id.co.quadras.siak.konsolidasi.scheduler engine
     * @param triggerKey schedule id to remove
     * @throws org.quartz.SchedulerException
     */
    public void remove(TriggerKey triggerKey) throws SchedulerException {
        scheduler.unscheduleJob(triggerKey);
    }

    /**
     * Get next date where the schedule will run
     * @param triggerKey id to query
     * @return Date when the schedule will run
     * @throws org.quartz.SchedulerException
     */
    public Date getNextRunningDate(TriggerKey triggerKey) throws SchedulerException {
        Date date = null;
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if (trigger != null) {
            date = trigger.getNextFireTime();
        }
        return date;
    }

    @MDCLog
    public JobDetail createJobDetail(Class<? extends Job> jobClass, JobKey jobKey, Map<String, Object> jobDataMap) {
        JobDetail jobDetail = newJob(jobClass).withIdentity(jobKey).build();
        if (jobDataMap != null && !jobDataMap.isEmpty())
            jobDetail.getJobDataMap().putAll(jobDataMap);

        LOGGER.debug("create jobDetail with jobDataMap = {}", jobDataMap);
        return jobDetail;
    }

    /**
     * Create trigger with interval web seconds
     * @param triggerKey id.co.quadras.siak.konsolidasi.scheduler id
     * @param interval web second
     * @param startNow if true the job will run now, if false the job will run after n seconds of interval
     * @return
     */
    @MDCLog
    public Trigger createIntervalTrigger(TriggerKey triggerKey, int interval, boolean startNow) {
        LOGGER.debug("triggerKey = {}", triggerKey);
        LOGGER.debug("interval = {}", interval);
        LOGGER.debug("startNow = {}", startNow);

        Trigger trigger;
        if (startNow) {
            trigger = newTrigger().withIdentity(triggerKey)
                    .withSchedule(simpleSchedule().withIntervalInSeconds(interval).repeatForever()).startNow().build();
        } else {
            trigger = newTrigger().withIdentity(triggerKey)
                    .withSchedule(simpleSchedule().withIntervalInSeconds(interval).repeatForever()).build();
        }

        return trigger;
    }

    @MDCLog
    public Trigger createFireOnceTrigger(TriggerKey triggerKey, int interval) {
        LOGGER.debug("triggerKey = {}", triggerKey);
        LOGGER.debug("interval = {}", interval);
        Trigger trigger = newTrigger().withIdentity(triggerKey)
                .startAt(futureDate(interval, DateBuilder.IntervalUnit.SECOND)).build();
        return trigger;
    }

    @MDCLog
    public Trigger createFixedTrigger(TriggerKey triggerKey, Date schedulerDate) {
        LOGGER.debug("triggerKey = {}", triggerKey);
        LOGGER.debug("schedulerDate = {}", schedulerDate);
        Trigger trigger = newTrigger()
                .withIdentity(triggerKey)
                .startAt(schedulerDate)
                .build();
        return trigger;
    }

    @MDCLog
    public Trigger createCronTrigger(TriggerKey triggerKey, String cronExpression) {
        LOGGER.debug("triggerKey = {}", triggerKey);
        LOGGER.debug("cronExpression = {}", cronExpression);
        if (!CronExpression.isValidExpression(cronExpression)) {
            throw new WinWorkException("Invalid cron expression = " + cronExpression);
        }
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        return trigger;
    }

    public TriggerKey createTriggerKey(String name) {
        return triggerKey(name);
    }

    public JobKey createJobKey(String name) {
        return jobKey(name);
    }

    public boolean isJobAlreadyExists(JobKey jobKey) throws SchedulerException {
        return scheduler.checkExists(jobKey);
    }

    public boolean isTriggerAlreadyExists(TriggerKey triggerKey) throws SchedulerException {
        return scheduler.checkExists(triggerKey);
    }

    public Date reschedule(TriggerKey triggerKey, Trigger newTrigger) throws SchedulerException {
        return scheduler.rescheduleJob(triggerKey, newTrigger);
    }

    public boolean isStarted() throws SchedulerException {
        return scheduler.isStarted();
    }
}
