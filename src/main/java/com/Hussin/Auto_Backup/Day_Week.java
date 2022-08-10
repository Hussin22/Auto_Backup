package com.Hussin.Auto_Backup;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

import static org.quartz.CronScheduleBuilder.weeklyOnDayAndHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;


public class Day_Week  {
    Scheduler scheduler;
public void weekly(String path, int hour, int minutes, int Day_WEEK) throws SchedulerException, IOException {

     scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();
    JobDetail job = newJob(Zipit.class)

            .withIdentity("ZipFile")
            .build();


    Trigger trigger = newTrigger()
            .withIdentity(triggerKey("myTrigger", "myTriggerGroup"))
            .withSchedule(weeklyOnDayAndHourAndMinute(Day_WEEK, hour, minutes))
            .build();


    scheduler.scheduleJob(job, trigger);

    Zipit zp = new Zipit();
    zp.setpath(path);

}
public void stoppro() throws SchedulerException {
    scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();
scheduler.shutdown();
}
    }




