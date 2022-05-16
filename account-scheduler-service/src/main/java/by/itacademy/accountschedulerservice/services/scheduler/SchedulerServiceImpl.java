package by.itacademy.accountschedulerservice.services.scheduler;

import by.itacademy.accountschedulerservice.controller.advice.ValidationException;
import by.itacademy.accountschedulerservice.model.TimeUnit;
import by.itacademy.accountschedulerservice.model.entity.ScheduledOperationEntity;
import by.itacademy.accountschedulerservice.services.api.SchedulerService;
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
    public void create(ScheduledOperationEntity scheduledOperation) {
        // Define job instance
        JobDetail job = JobBuilder.newJob(CreateOperationJob.class)
                .withIdentity(scheduledOperation.getUuid().toString(), "operations")
                .usingJobData("operation", scheduledOperation.getUuid().toString())
                .build();

        LocalDateTime start_time = LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledOperation.getStart_time()), ZoneId.systemDefault());
        LocalDateTime stop_time = LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledOperation.getStop_time()), ZoneId.systemDefault());

        int interval = scheduledOperation.getInterval();
        TimeUnit time_unit = TimeUnit.valueOf(scheduledOperation.getTime_unit());

        TriggerBuilder<Trigger> builder = TriggerBuilder.newTrigger()
                .withIdentity(scheduledOperation.getUuid().toString(), "operations");

//        if (start_time == null) {
//            builder.startNow();
//            start_time = LocalDateTime.now();
//        } else {
            builder.startAt(this.conversionService.convert(start_time, Date.class));
//        }
        if (interval > 0) {
            SimpleScheduleBuilder ssb = null;
            CronScheduleBuilder csb = null;
            String expression;

            switch (time_unit) {
                case SECOND:
                    ssb = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds((int) interval);
                    break;
                case MINUTE:
                    ssb = SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes((int) interval);
                    break;
                case HOUR:
                    ssb = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours((int) interval);
                    break;
                case DAY:
                    ssb = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours((int) (interval * 24));
                    break;
                case WEEK:
                    ssb = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours((int) (interval * 24 * 7));
                    break;
                case MONTH:
                    expression = String.format("%d %d %d %d * ?",
                            start_time.getSecond(),
                            start_time.getMinute(),
                            start_time.getHour(),
                            start_time.getDayOfMonth());
                    csb = CronScheduleBuilder.cronSchedule(expression);
                    break;
                case YEAR:
                    expression = String.format("%d %d %d %d %d ?",
                            start_time.getSecond(),
                            start_time.getMinute(),
                            start_time.getHour(),
                            start_time.getDayOfMonth(),
                            start_time.getMonthValue());
                    csb = CronScheduleBuilder.cronSchedule(expression);
                    break;
            }

            if (ssb != null) {
                builder.withSchedule(ssb.repeatForever());
            } else if (csb != null) {
                builder.withSchedule(csb);
            }
        }
        else {
            throw new ValidationException("interval не может быть меньше 1");
        }
            builder.endAt(this.conversionService.convert(stop_time, Date.class));

        Trigger trigger = builder.build();

        try {
            this.scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException("Ошибка создания запланированной операции", e);
        }
    }
}
