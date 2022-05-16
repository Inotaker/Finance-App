package by.itacademy.accountschedulerservice.services.impl;

import by.itacademy.accountschedulerservice.controller.advice.ValidationError;
import by.itacademy.accountschedulerservice.controller.advice.ValidationException;
import by.itacademy.accountschedulerservice.dao.api.IScheduledOperationStorage;
import by.itacademy.accountschedulerservice.model.Page;
import by.itacademy.accountschedulerservice.model.TimeUnit;
import by.itacademy.accountschedulerservice.model.dto.OperationHttpPost;
import by.itacademy.accountschedulerservice.model.dto.ScheduledOperation;
import by.itacademy.accountschedulerservice.model.entity.ScheduledOperationEntity;
import by.itacademy.accountschedulerservice.services.api.ScheduledOperationService;
import by.itacademy.accountschedulerservice.services.scheduler.SchedulerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduledOperationServiceImpl implements ScheduledOperationService {

    private final ConversionService conversionService;
    private final SchedulerServiceImpl schedulerService;
    private final IScheduledOperationStorage storage;

    public ScheduledOperationServiceImpl(ConversionService conversionService, SchedulerServiceImpl schedulerService, IScheduledOperationStorage storage) {
        this.conversionService = conversionService;
        this.schedulerService = schedulerService;
        this.storage = storage;
    }

    @Override
    public ScheduledOperationEntity add(ScheduledOperationEntity scheduledOperation) {
        List<ValidationError> errors = new ArrayList<>();
        int countError = 0;
        if (scheduledOperation.getDescription().equals("")) {
            errors.add(new ValidationError("error", "поле description не может быть пустым"));
            countError++;
        }
        if (scheduledOperation.getInterval() == 0) {
            errors.add(new ValidationError("error", "поле interval не может быть нулем"));
            countError++;
        }
        if (scheduledOperation.getStart_time() == 0) {
            errors.add(new ValidationError("error", "поле start_time не может быть нулем"));
            countError++;
        }
        if (scheduledOperation.getStop_time() == 0) {
            errors.add(new ValidationError("error", "поле stop_time не может быть нулем"));
            countError++;
        }
        if (scheduledOperation.getValue() == 0) {
            errors.add(new ValidationError("error", "значение операции не может быть равно нулю"));
            countError++;
        }
        try {
            scheduledOperation.setTime_unit(TimeUnit.valueOf(scheduledOperation.getTime_unit()).toString());
        } catch (IllegalArgumentException e) {
            errors.add(new ValidationError("error", "поле time_unit не соответствую ни одному enum"));
        }
        if (countError > 0) throw new ValidationException("ошибка валидация запланированной операции", errors);
//        try {
//            sendPost(OperationPost.Builder.anOperation()
//                    .withCategory(scheduledOperation.getCategory())
//                    .withCurrency(scheduledOperation.getCurrency())
//                    .withDescription(scheduledOperation.getDescription())
//                    .withValue(scheduledOperation.getValue())
//                    .withDate(0)
//                    .build(), scheduledOperation.getAccount());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        UUID scheduledUuid = UUID.randomUUID();

        long time = System.currentTimeMillis();
        scheduledOperation.setDt_create(time);
        scheduledOperation.setDt_update(time);
        scheduledOperation.setUuid(scheduledUuid);

        ScheduledOperationEntity scheduledOperationEntity = storage.save(scheduledOperation);

        this.schedulerService.create(getById(scheduledUuid));
        return scheduledOperationEntity;
    }

    @Override
    public ScheduledOperationEntity getById(UUID uuid) {
        return storage.findById(uuid).get();
    }


    @Override
    public ScheduledOperationEntity editScheduledOperation(UUID uuid, ScheduledOperation scheduledOperation, long dt_update) {
        ScheduledOperationEntity scheduledOperationEntityAdd = getById(uuid);

        if (scheduledOperationEntityAdd.getUuid().equals(uuid)) {
            if (scheduledOperationEntityAdd.getDt_update() == dt_update) {
                scheduledOperationEntityAdd.setValue(scheduledOperation.getOperation().getValue());
                scheduledOperationEntityAdd.setAccount(scheduledOperation.getOperation().getAccount());
                scheduledOperationEntityAdd.setCurrency(scheduledOperation.getOperation().getCurrency());
                scheduledOperationEntityAdd.setDescription(scheduledOperation.getOperation().getDescription());

                scheduledOperationEntityAdd.setInterval(scheduledOperation.getSchedule().getInterval());
                scheduledOperationEntityAdd.setStop_time(scheduledOperation.getSchedule().getStop_time());
                scheduledOperationEntityAdd.setTime_unit(scheduledOperation.getSchedule().getTime_unit());
                scheduledOperationEntityAdd.setStart_time(scheduledOperation.getSchedule().getStart_time());

                scheduledOperationEntityAdd.setDt_update(System.currentTimeMillis());
            } else throw new ValidationException("dt_update не совпадает");
        } else throw new ValidationException("нет запланированных операций с этим uuid");
        return storage.save(scheduledOperationEntityAdd);
    }

    @Override
    public ScheduledOperationEntity convertToEntity(ScheduledOperation scheduledOperation) {
        return conversionService.convert(scheduledOperation, ScheduledOperationEntity.class);
    }

    @Override
    public Page<ScheduledOperation> getPage(String size, String page) {
        Page<ScheduledOperation> accountPage = new Page<>();

        int p = Integer.parseInt(page) - 1;/**отсчет страниц идет с нуля*/
        int s = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(p, s);
        List<ScheduledOperationEntity> scheduledOperations = this.storage.findAllBy(pageable);
        List<ScheduledOperation> scheduledOperationDTOS = new ArrayList<>();
        for (ScheduledOperationEntity scheduledOperation : scheduledOperations) {
            scheduledOperationDTOS.add(this.conversionService.convert(scheduledOperation, ScheduledOperation.class));
        }
        accountPage.setContent(scheduledOperationDTOS);
        if (!pageable.hasPrevious()) {
            accountPage.setFirst(true);
        }

        int totalElement = this.storage.findAll().size();
        int totalPage = totalElement / s;

        if (totalElement % s > 0) {/**Если есть остаток после деления, значит есть неполная страница*/
            totalPage++;
        }
        if (pageable.getPageSize() > scheduledOperations.size() || pageable.getPageSize() == totalElement) {
            accountPage.setLast(true);
        }
        accountPage.setTotal_pages(totalPage);
        accountPage.setTotal_element(totalElement);
        accountPage.setNumber_of_elements(scheduledOperations.size());
        accountPage.setSize(pageable.getPageSize());
        accountPage.setNumber(pageable.getPageNumber() + 1);/**отсчет страниц идет с нуля*/
        return accountPage;
    }

    private void sendPost(OperationHttpPost operation, UUID account) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpPost request = new HttpPost("http://localhost:8080/account/" + account + "/operation");
            StringEntity params = new StringEntity(mapper.writeValueAsString(operation));
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().toString());
        } catch (Exception ex) {
        }


    }

}
