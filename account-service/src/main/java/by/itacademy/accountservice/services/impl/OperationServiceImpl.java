package by.itacademy.accountservice.services.impl;

import by.itacademy.accountservice.controller.advice.ValidationError;
import by.itacademy.accountservice.controller.advice.ValidationException;
import by.itacademy.accountservice.dao.api.IAccountStorage;
import by.itacademy.accountservice.dao.api.IOperationStorage;
import by.itacademy.accountservice.model.dto.Operation;
import by.itacademy.accountservice.model.dto.Page;
import by.itacademy.accountservice.model.entity.OperationEntity;
import by.itacademy.accountservice.services.api.OperationService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
        List<ValidationError> errors = new ArrayList<>();

        if (operationEntity.getDescription().equals("")) {
            errors.add(new ValidationError("error", "поле description не может быть пустым"));
        }
        if (operationEntity.getValue() == 0) {
            errors.add(new ValidationError("error", "Сумма операции не может быть нулем"));
        }
        if (operationEntity.getDate() == 0) {
            errors.add(new ValidationError("error", "поле date не может быть нулем"));
        }
        /**проверка валюты*/
        try {
            final String USER_AGENT = "Mozilla/5.0";

            String url = "http://localhost:8084/classifier/currency/" + operationEntity.getCurrency();

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Значение по умолчанию - GET
            con.setRequestMethod("GET");

            // Добавляем заголовок запроса
            con.setRequestProperty("User-Agent", USER_AGENT);
            if (con.getResponseCode() == 204) {
                errors.add(new ValidationError("error", "Валюты не существует"));
            }
            System.out.println(con.getResponseCode());


            String url1 = "http://localhost:8084/classifier/operation/category/" + operationEntity.getCategory();

            URL obj1 = new URL(url1);
            HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection();

            // Значение по умолчанию - GET
            con1.setRequestMethod("GET");

            // Добавляем заголовок запроса
            con1.setRequestProperty("User-Agent", USER_AGENT);

            if (con1.getResponseCode() == 204) {
                errors.add(new ValidationError("error", "Категории не существует"));
            }
            System.out.println(con1.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**проверка категории операции*/

        if (service.getById(uuid).getUuid().equals(uuid)) {
            long time = System.currentTimeMillis();
            service.editBalance(operationEntity.getValue(), uuid);
            operationEntity.setAccount(uuid);
            operationEntity.setDt_create(time);
            operationEntity.setDt_update(time);
            operationEntity.setUuid(UUID.randomUUID());
            operationEntity.setAvailable(true);/**операция доступна*/
        }
        else {
            errors.add(new ValidationError("error", "счета с таким uuid не существует"));
        }
        if (errors.size() > 0) throw new ValidationException("ошибка валидация аккаунта", errors);
        return this.storage.save(operationEntity);
    }

//    private void checkCategory(UUID category, List<ValidationError> errors) throws IOException {
//        final String USER_AGENT = "Mozilla/5.0";
//
//        String url = "http://localhost:8084/classifier/operation/category" + category;
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // Значение по умолчанию - GET
//        con.setRequestMethod("GET");
//
//        // Добавляем заголовок запроса
//        con.setRequestProperty("User-Agent", USER_AGENT);
//
//        if (con.getResponseCode() == 204) {
//            errors.add(new ValidationError("error", "Категории не существует"));
//        }
//    }

//    private void checkCurrency(UUID currency, List<ValidationError> errors) throws IOException {
//        final String USER_AGENT = "Mozilla/5.0";
//
//        String url = "http://localhost:8084/classifier/currency/" + currency;
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // Значение по умолчанию - GET
//        con.setRequestMethod("GET");
//
//        // Добавляем заголовок запроса
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        if (con.getResponseCode() == 204) {
//            errors.add(new ValidationError("error", "Валюты не существует"));
//        }
//    }

    @Override
    public OperationEntity getById(UUID uuid) {
        OperationEntity operationEntity = this.storage.findById(uuid).get();
        if (!operationEntity.isAvailable())
            throw new ValidationException("Эта операция недоступна!");
        return operationEntity;
        /**ЧТО ЗА МАГИЯ!
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

    private int sendGet(UUID uuid) throws Exception {
        final String USER_AGENT = "Mozilla/5.0";

        String url = "http://localhost:8084/classifier/currency/" + uuid;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Значение по умолчанию - GET
        con.setRequestMethod("GET");

        // Добавляем заголовок запроса
        con.setRequestProperty("User-Agent", USER_AGENT);
        return con.getResponseCode();
    }

}
