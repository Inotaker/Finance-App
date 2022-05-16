package by.itacademy.accountschedulerservice.model.converter;

import by.itacademy.accountschedulerservice.model.dto.Operation;
import by.itacademy.accountschedulerservice.model.dto.Schedule;
import by.itacademy.accountschedulerservice.model.dto.ScheduledOperation;
import by.itacademy.accountschedulerservice.model.entity.ScheduledOperationEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScheduledOperationEntityToDto implements Converter<ScheduledOperationEntity, ScheduledOperation> {
    @Override
    public ScheduledOperation convert(ScheduledOperationEntity source) {
        return ScheduledOperation
                .Builder
                .aScheduledOperation()
                .withOperation(Operation.Builder.anOperation()
                        .withAccount(source.getAccount())
                        .withCategory(source.getCategory())
                        .withCurrency(source.getCurrency())
                        .withValue(source.getValue())
                        .withDescription(source.getDescription())
                        .build())
                .withSchedule(Schedule.Builder.aSchedule()
                        .withInterval(source.getInterval())
                        .withStart_time(source.getStart_time())
                        .withStop_time(source.getStop_time())
                        .withTime_unit(source.getTime_unit())
                        .build())
                .withDt_create(source.getDt_create())
                .withDt_update(source.getDt_update())
                .withUuid(source.getUuid())
                .build();
    }
}
