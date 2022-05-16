package by.itacademy.classifierservice.services.impl;

import by.itacademy.classifierservice.controller.advice.ValidationException;
import by.itacademy.classifierservice.dao.api.IOperationCategoryStorage;
import by.itacademy.classifierservice.model.Page;
import by.itacademy.classifierservice.model.dto.OperationCategory;
import by.itacademy.classifierservice.model.entity.OperationCategoryEntity;
import by.itacademy.classifierservice.services.OperationCategoryService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationCategoryServiceImpl implements OperationCategoryService {

    private final ConversionService conversionService;
    private final IOperationCategoryStorage storage;

    public OperationCategoryServiceImpl(ConversionService conversionService, IOperationCategoryStorage storage) {
        this.conversionService = conversionService;
        this.storage = storage;
    }

    @Override
    public OperationCategoryEntity add(OperationCategoryEntity operationCategoryEntity) {
        if(this.storage.existsByTitle(operationCategoryEntity.getTitle())){
            throw new ValidationException("такая категория уже существует");
        }
        return this.storage.save(operationCategoryEntity);
    }

    @Override
    public Page<OperationCategory> getPage(String size, String page) {

        Page<OperationCategory> categoryPage = new Page<>();

        int p = Integer.parseInt(page) - 1;/**отсчет страниц идет с нуля*/
        int s = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(p, s);
        List<OperationCategoryEntity> operationCategoryEntities = this.storage.findAllBy(pageable);
        List<OperationCategory> operationCategories = new ArrayList<>();
        for (OperationCategoryEntity operationCategoryEntity : operationCategoryEntities) {
            operationCategories.add(convertToDto(operationCategoryEntity));
        }
        categoryPage.setContent(operationCategories);
        if (!pageable.hasPrevious()) {
            categoryPage.setFirst(true);
        }

        int totalElement = this.storage.findAll().size();
        int totalPage = totalElement / s;

        if (totalElement % s > 0) {/**Если есть остаток после деления, значит есть неполная страница*/
            totalPage++;
        }
        if (pageable.getPageSize() > operationCategoryEntities.size() || pageable.getPageSize() == totalElement) {
            categoryPage.setLast(true);
        }
        categoryPage.setTotal_pages(totalPage);
        categoryPage.setTotal_element(totalElement);
        categoryPage.setNumber_of_elements(operationCategoryEntities.size());
        categoryPage.setSize(pageable.getPageSize());
        categoryPage.setNumber(pageable.getPageNumber() + 1);/**отсчет страниц идет с нуля*/
        return categoryPage;
    }

    private OperationCategory convertToDto(OperationCategoryEntity operationCategoryEntity) {
        return this.conversionService.convert(operationCategoryEntity, OperationCategory.class);
    }

    @Override
    public OperationCategoryEntity convertToEntity(OperationCategory operationCategory) {
        return this.conversionService.convert(operationCategory, OperationCategoryEntity.class);
    }
}
