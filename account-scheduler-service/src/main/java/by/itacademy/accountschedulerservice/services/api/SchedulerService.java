package by.itacademy.accountschedulerservice.services.api;

import by.itacademy.accountschedulerservice.model.entity.ScheduledOperationEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface SchedulerService {

    void create(ScheduledOperationEntity operation);
}
