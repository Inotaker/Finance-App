package by.itacademy.accountschedulerservice.services.scheduler;

import by.itacademy.accountschedulerservice.model.TimeUnit;
import by.itacademy.accountschedulerservice.model.entity.ScheduledOperationEntity;
import by.itacademy.accountschedulerservice.services.SchedulerService;
import org.quartz.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final Scheduler scheduler;
    private final ConversionService conversionService;

    public SchedulerServiceImpl(Scheduler scheduler, ConversionService conversionService) {
        this.scheduler = scheduler;
        this.conversionService = conversionService;
    }

    @Override
    public void create(ScheduledOperationEntity operation) {
        LocalDateTime start_time = LocalDateTime.ofInstant(Instant.ofEpochMilli(operation.getStart_time()), ZoneId.systemDefault());
        LocalDateTime stop_time = LocalDateTime.ofInstant(Instant.ofEpochMilli(operation.getStop_time()), ZoneId.systemDefault());
        int interval = operation.getInterval();
        TimeUnit time_unit = TimeUnit.valueOf(operation.getTime_unit());
        long millis_to_time_unit = 0;

        SimpleScheduleBuilder simpleScheduleBuilder = null;
        CronScheduleBuilder cronScheduleBuilder = null;
        String expression;

        switch (time_unit) {
            case SECOND:
                simpleScheduleBuilder.withIntervalInSeconds(interval);
                break;
            case MINUTE:
                simpleScheduleBuilder.withIntervalInMinutes(interval);
                break;
            case HOUR:
                simpleScheduleBuilder.withIntervalInHours(interval);
                break;
            case DAY:
                simpleScheduleBuilder.withIntervalInHours(interval * 24);
                break;
            case WEEK:
                simpleScheduleBuilder.withIntervalInHours(interval * 24 * 7);
                break;

            case MONTH:
                expression = String.format("%d %d %d %d * ?",
                        start_time.getSecond(),
                        start_time.getMinute(),
                        start_time.getHour(),
                        start_time.getDayOfMonth());
                cronScheduleBuilder = CronScheduleBuilder.cronSchedule(expression);
                break;
            case YEAR:
                expression = String.format("%d %d %d %d %d ?",
                        start_time.getSecond(),
                        start_time.getMinute(),
                        start_time.getHour(),
                        start_time.getDayOfMonth(),
                        start_time.getMonthValue());
                cronScheduleBuilder = CronScheduleBuilder.cronSchedule(expression);
                break;
        }
        // Define job instance
        JobDetail job1 = JobBuilder.newJob(CreateOperationJob.class)
                .withIdentity(operation.getUuid().toString(), "operations")
                .usingJobData("operation", operation.getUuid().toString())
                .build();

// Define a Trigger that will fire "now", and not repeat
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(operation.getUuid().toString(), "operations")
                .startAt(this.conversionService.convert(start_time,Date.class))/**start_time*/
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMilliseconds(millis_to_time_unit)
                        .withIntervalInSeconds(interval) /**time_unit*/
                        .repeatForever())
                .endAt(this.conversionService.convert(stop_time,Date.class))/**stop_time*/
                .build();

// Schedule the job with the trigger
        try {
            scheduler.scheduleJob(job1, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e); //TODO ошибка
        }
    }

//    @Override
//    public void create(UUID operation, String time_unit, long start_time, long stop_time) {
//        // Define job instance
//        JobDetail job1 = JobBuilder.newJob(CreateOperationJob.class)
//                .withIdentity(operation, "operations")
//                .usingJobData("operation", operation.toString())
//                .build();
//
//// Define a Trigger that will fire "now", and not repeat
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity(operation.toString(), "operations")
//                .startAt(new Date(start_time))
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10))
//                .endAt(new Date(stop_time))/**start_time*/
//                .build();
//
//// Schedule the job with the trigger
//        try {
//            scheduler.scheduleJob(job1, trigger);
//        } catch (SchedulerException e) {
//            throw new RuntimeException(e); //TODO ошибка
//        }
//    }
//@Override
//public void create(ScheduledOperationEntity operation) {
//    // Define job instance
//    JobDetail job1 = JobBuilder.newJob(CreateOperationJob.class)
//            .withIdentity(operation.getUuid().toString(), "operations")
//            .usingJobData("operation", operation.getUuid().toString())
//            .build();
//
//// Define a Trigger that will fire "now", and not repeat
//    Trigger trigger = TriggerBuilder.newTrigger()
//            .withIdentity(operation.getUuid().toString(), "operations")
//            .startAt(new Date(System.currentTimeMillis()))/**start_time*/
//            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                    .withIntervalInSeconds(10) /**time_unit*/
//                    .repeatForever())/**stop_time*/
//            .build();
//
//// Schedule the job with the trigger
//    try {
//        scheduler.scheduleJob(job1, trigger);
//    } catch (SchedulerException e) {
//        throw new RuntimeException(e); //TODO ошибка
//    }
//}
}
