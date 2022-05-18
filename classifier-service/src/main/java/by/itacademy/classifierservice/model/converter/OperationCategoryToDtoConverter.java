package by.itacademy.classifierservice.model.converter;

import by.itacademy.classifierservice.model.dto.OperationCategory;
import by.itacademy.classifierservice.model.entity.OperationCategoryEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OperationCategoryToDtoConverter implements Converter<OperationCategory, OperationCategoryEntity> {
    @Override
    public OperationCategoryEntity convert(OperationCategory source) {
        OperationCategoryEntity operationCategoryEntity = OperationCategoryEntity
                .Builder
                .anOperationCategoryEntity()
                .withTitle(source.getTitle())
                .withDt_create(source.getDt_create())
                .withDt_update(source.getDt_update())
                .withUuid(source.getUuid())
                .build();
        return operationCategoryEntity;
    }
}
