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
                .OperationCategoryEntityBuilder
                .anOperationCategoryEntity()
                .withTitle(source.getTitle())
                .build();
        return operationCategoryEntity;
    }
}
