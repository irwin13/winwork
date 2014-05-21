package com.irwin13.winwork.basic.scheduler;

import com.google.inject.Provider;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irwin Timestamp : 21/05/2014 16:48
 */
public class SchedulerProvider implements Provider<Scheduler> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerProvider.class);
    private Scheduler scheduler;

    @Override
    public Scheduler get() {
        if (scheduler == null) {
            try {
                scheduler = StdSchedulerFactory.getDefaultScheduler();
            } catch (SchedulerException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        }
        return scheduler;
    }
}
