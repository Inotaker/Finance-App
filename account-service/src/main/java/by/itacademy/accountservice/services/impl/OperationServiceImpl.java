package by.itacademy.accountservice.services.impl;

import by.itacademy.accountservice.controller.advice.ValidationException;
import by.itacademy.accountservice.dao.api.IAccountStorage;
import by.itacademy.accountservice.dao.api.IOperationStorage;
import by.itacademy.accountservice.model.dto.Operation;
import by.itacademy.accountservice.model.dto.Page;
import by.itacademy.accountservice.model.entity.OperationEntity;
import by.itacademy.accountservice.services.OperationService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OperationServiceImpl implements OperationService {

    private final ConversionService conversionService;
    private final IOperationStorage storage;
    private final AccountServiceImpl service;

    public OperationServiceImpl(IOperationStorage storage, IAccountStorage accountStorage, ConversionService conversionService, AccountServiceImpl service) {
        this.storage = storage;
        this.conversionService = conversionService;
        this.service = service;
    }

    @Override
    public OperationEntity addOperation(OperationEntity operationEntity, UUID uuid) {
        if (service.getById(uuid).getUuid().equals(uuid)) {
            operationEntity.setAccount(uuid);
            service.editBalance(operationEntity.getValue(),uuid);
            return this.storage.save(operationEntity);
        } else {
            throw new ValidationException("счета с таким uuid не существует");
        }
    }

    @Override
    public OperationEntity getById(UUID uuid) {
        OperationEntity operationEntity = this.storage.findById(uuid).get();
        if (!operationEntity.isAvailable())
            throw new ValidationException("Эта операция недоступна!");
        return operationEntity;
        /**ЧТО БЛЯТЬ ЗА МАГИЯ!
         https://stackoverflow.com/questions/52656517/no-serializer-found-for-class-org-hibernate-proxy-pojo-bytebuddy-bytebuddyinterc
         */
    }


    @Override
    public OperationEntity editOperation(UUID uuid, UUID uuid_operation, String dt_update, OperationEntity operationEntity) {/**сверка dt_update*/
        OperationEntity operation = getById(uuid_operation);
        if (operation.getAccount().equals(uuid))
            throw new ValidationException("на этом счету нет этой операции");
        operation.setDate(operationEntity.getDate());
        operation.setDescription(operationEntity.getDescription());
        operation.setCategory(operationEntity.getCategory());
        operation.setCurrency(operationEntity.getCurrency());
        operation.setValue(operationEntity.getValue());
        operation.setDt_update(System.currentTimeMillis());
        return addOperation(operation, uuid);
    }


    @Override
    public Page<Operation> getPage(String size, String page, UUID uuid) {/**where is available true!!!!!*/
        Page<Operation> accountPage = new Page<>();

        int p = Integer.parseInt(page) - 1;/**отсчет страниц идет с нуля*/
        int s = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(p, s);
        List<Operation> operations = new ArrayList<>();
        List<OperationEntity> operationEntities = this.storage.findAllByAccountAndAvailable(pageable, uuid, true);
        if (operationEntities.size() == 0) throw new ValidationException("по этому аккаунту нет операций!");
        for (OperationEntity operationEntity : operationEntities) {
            operations.add(convertToDto(operationEntity));
        }
        accountPage.setContent(operations);
        if (!pageable.hasPrevious()) {
            accountPage.setFirst(true);
        }

        int totalElement = this.storage.findAllByAccountAndAvailable(uuid, true).size();
        int totalPage = totalElement / s;

        if (totalElement % s > 0) {/**Если есть остаток после деления, значит есть неполная страница*/
            totalPage++;
        }
        if (pageable.getPageSize() > operationEntities.size() || pageable.getPageSize() == totalElement) {
            accountPage.setLast(true);
        }
        accountPage.setTotal_pages(totalPage);
        accountPage.setTotal_element(totalElement);
        accountPage.setNumber_of_elements(operationEntities.size());
        accountPage.setSize(pageable.getPageSize());
        accountPage.setNumber(pageable.getPageNumber() + 1);/**отсчет страниц идет с нуля*/
        return accountPage;
    }

    @Override
    public Operation convertToDto(OperationEntity operationEntity) {
        return this.conversionService.convert(operationEntity, Operation.class);
    }

    @Override
    public OperationEntity convertToEntity(Operation operation) {
        return this.conversionService.convert(operation, OperationEntity.class);
    }

    @Override
    public boolean delete(UUID accountId, UUID operationId, String dt_update) {
        long dt_long = Long.parseLong(dt_update);
        OperationEntity operationEntity = storage.getById(operationId);
        if (!operationEntity.getAccount().equals(accountId))
            throw new ValidationException("не соотвествует uuid.operation с uuid.account");
        if (operationEntity.getDt_update() == dt_long) throw new ValidationException("dt_update не совпадает");
        operationEntity.setAvailable(false);
        storage.save(operationEntity);
        return true;
    }

}
