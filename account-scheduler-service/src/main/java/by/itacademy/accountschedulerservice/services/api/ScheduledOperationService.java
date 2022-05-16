package by.itacademy.accountschedulerservice.services.api;

import by.itacademy.accountschedulerservice.model.Page;
import by.itacademy.accountschedulerservice.model.entity.ScheduledOperationEntity;
import by.itacademy.accountschedulerservice.model.dto.ScheduledOperation;

import java.util.UUID;

public interface ScheduledOperationService {

    ScheduledOperationEntity add(ScheduledOperationEntity scheduledOperation);

    ScheduledOperationEntity getById(UUID uuid);

    ScheduledOperationEntity editScheduledOperation(UUID uuid, ScheduledOperation scheduledOperation, long dt_update);

    ScheduledOperationEntity convertToEntity(ScheduledOperation scheduledOperation);

    Page<ScheduledOperation> getPage(String size, String page);
}
