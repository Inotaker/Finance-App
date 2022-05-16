package by.itacademy.accountservice.model.converter;

import by.itacademy.accountservice.model.dto.Operation;
import by.itacademy.accountservice.model.entity.OperationEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OperationToEntityConverter implements Converter<Operation, OperationEntity> {
    @Override
    public OperationEntity convert(Operation source) {
        return OperationEntity
                .Builder
                .anOperationEntity()
                .withAccount(source.getAccount())
                .withCategory(source.getCategory())
                .withCurrency(source.getCurrency())
                .withDate(source.getDate())
                .withDescription(source.getDescription())
                .withValue(source.getValue())
                .withDt_create(source.getDt_create())
                .withDt_update(source.getDt_update())
                .withUuid(source.getUuid())
                .build();
    }
}
