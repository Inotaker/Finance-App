package by.itacademy.accountschedulerservice.services.scheduler;


import by.itacademy.accountschedulerservice.services.api.SchedulerService;
import by.itacademy.accountschedulerservice.services.impl.ScheduledOperationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/scheduler")
public class SchedulerTest {

    private final SchedulerService schedulerService;
    private final ScheduledOperationServiceImpl scheduledOperationService;

    public SchedulerTest(SchedulerService schedulerService, ScheduledOperationServiceImpl scheduledOperationService) {
        this.schedulerService = schedulerService;
        this.scheduledOperationService = scheduledOperationService;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public void create(){
//        this.schedulerService.create(UUID.randomUUID());
//    }
}
