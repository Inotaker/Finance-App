package by.itacademy.classifierservice.services;

import by.itacademy.classifierservice.model.Page;
import by.itacademy.classifierservice.model.dto.Currency;
import by.itacademy.classifierservice.model.dto.OperationCategory;
import by.itacademy.classifierservice.model.entity.OperationCategoryEntity;

public interface OperationCategoryService {
    OperationCategoryEntity add(OperationCategoryEntity operationCategoryEntity);

    OperationCategoryEntity convertToEntity(OperationCategory operationCategory);

    Page<OperationCategory> getPage(String size, String page);
}
