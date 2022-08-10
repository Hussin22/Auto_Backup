package com.Hussin.Auto_Backup;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.CronScheduleBuilder.weeklyOnDayAndHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;


public class Daily {

public void Daily(String path, int hour, int minutes) throws SchedulerException, IOException {

    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();
    JobDetail job = newJob(Zipit.class)

            .withIdentity("ZipFile")
            .build();


    Trigger trigger = newTrigger()
            .withIdentity(triggerKey("myTrigger", "myTriggerGroup"))
            .withSchedule(dailyAtHourAndMinute(hour,minutes))
            .build();

    scheduler.scheduleJob(job, trigger);
    Zipit zp = new Zipit();
    zp.setpath(path);

}
    }




