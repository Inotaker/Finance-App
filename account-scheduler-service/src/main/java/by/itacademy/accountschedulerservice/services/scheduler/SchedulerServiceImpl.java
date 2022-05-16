package by.itacademy.accountschedulerservice.services.scheduler;

import by.itacademy.accountschedulerservice.model.TimeUnit;
import by.itacademy.accountschedulerservice.model.entity.ScheduledOperationEntity;
import by.itacademy.accountschedulerservice.services.SchedulerService;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final Scheduler scheduler;

    public SchedulerServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void create(ScheduledOperationEntity operation) {
        long start_time = operation.getStart_time();
        long stop_time = operation.getStop_time();
        int interval = operation.getInterval();
        TimeUnit time_unit = TimeUnit.valueOf(operation.getTime_unit());
        long millis_to_time_unit = 0;

        SimpleScheduleBuilder simpleScheduleBuilder = null;

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
                simpleScheduleBuilder.withIntervalInHours(interval*24);
                break;
            case WEEK:
                simpleScheduleBuilder.withIntervalInHours(interval*24*7);
                break;
            case MONTH:
                millis_to_time_unit = java.util.concurrent.TimeUnit.;
                break;
            case YEAR:
                millis_to_time_unit = java.util.concurrent.TimeUnit.DAYS.toMillis(1;
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
                .startAt(new Date(start_time))/**start_time*/
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMilliseconds(millis_to_time_unit)
                        .withIntervalInSeconds(interval) /**time_unit*/
                        .repeatForever())
                .endAt(new Date(stop_time))/**stop_time*/
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
