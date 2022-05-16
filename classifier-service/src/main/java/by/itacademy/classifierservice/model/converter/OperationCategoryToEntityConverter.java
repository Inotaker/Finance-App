package by.itacademy.classifierservice.model.converter;

import by.itacademy.classifierservice.model.dto.OperationCategory;
import by.itacademy.classifierservice.model.entity.OperationCategoryEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OperationCategoryToEntityConverter implements Converter<OperationCategoryEntity, OperationCategory> {
    @Override
    public OperationCategory convert(OperationCategoryEntity source) {
        OperationCategory operationCategory = OperationCategory
                .Builder
                .anOperationCategory()
                .withTitle(source.getTitle())
                .withDt_create(source.getDt_create())
                .withDt_update(source.getDt_update())
                .withUuid(source.getUuid())
                .build();
        return operationCategory;
    }
}
