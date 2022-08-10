package com.Hussin.Auto_Backup;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;


public class Hourly {

public void Hourly(String path) throws SchedulerException, IOException {

    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();
    JobDetail job = newJob(Zipit.class)

            .withIdentity("ZipFile")
            .build();



  SimpleTrigger trigger = newTrigger().withIdentity("myTrigger")
            .startNow()
            .withSchedule(SimpleScheduleBuilder.repeatHourlyForever())
            .build();

    scheduler.scheduleJob(job, trigger);
    Zipit zp = new Zipit();
    zp.setpath(path);

}
    }




