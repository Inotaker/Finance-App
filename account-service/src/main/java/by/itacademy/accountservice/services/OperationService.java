package by.itacademy.accountservice.services;

import by.itacademy.accountservice.model.dto.Operation;
import by.itacademy.accountservice.model.entity.OperationEntity;
import by.itacademy.accountservice.model.dto.Page;

import java.util.UUID;

public interface OperationService {
    OperationEntity addOperation(OperationEntity operationEntity, UUID uuid);

    OperationEntity getById(UUID uuid);

    OperationEntity editOperation(UUID uuid, UUID uuid_operation, String dt_update, OperationEntity operationEntity);

    Page<Operation> getPage(String size, String page, UUID uuid);

    boolean delete(UUID operationUuid, UUID uuid_operation, String dt_update);

    Operation convertToDto(OperationEntity operationEntity);

    OperationEntity convertToEntity(Operation operation);
}
