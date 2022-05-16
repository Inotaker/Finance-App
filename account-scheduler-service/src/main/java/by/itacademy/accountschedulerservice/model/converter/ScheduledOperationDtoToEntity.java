package by.itacademy.accountschedulerservice.model.converter;

import by.itacademy.accountschedulerservice.model.dto.ScheduledOperation;
import by.itacademy.accountschedulerservice.model.entity.ScheduledOperationEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScheduledOperationDtoToEntity implements Converter<ScheduledOperation, ScheduledOperationEntity> {
    @Override
    public ScheduledOperationEntity convert(ScheduledOperation source) {
        return ScheduledOperationEntity.Builder
                .aScheduledOperationEntity()
                .withInterval(source.getSchedule().getInterval())
                .withStart_time(source.getSchedule().getStart_time())
                .withStop_time(source.getSchedule().getStop_time())
                .withTime_unit(source.getSchedule().getTime_unit())

                .withAccount(source.getOperation().getAccount())
                .withCurrency(source.getOperation().getCurrency())
                .withDescription(source.getOperation().getDescription())
                .withValue(source.getOperation().getValue())
                .build();
    }
}
