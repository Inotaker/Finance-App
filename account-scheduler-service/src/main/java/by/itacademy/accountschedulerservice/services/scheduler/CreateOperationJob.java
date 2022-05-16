package by.itacademy.accountschedulerservice.services.scheduler;

import by.itacademy.accountschedulerservice.model.dto.OperationHttpPost;
import by.itacademy.accountschedulerservice.model.entity.ScheduledOperationEntity;
import by.itacademy.accountschedulerservice.services.impl.ScheduledOperationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateOperationJob implements Job {
    private final ScheduledOperationServiceImpl service;

    public CreateOperationJob(ScheduledOperationServiceImpl service) {
        this.service = service;
    }
    //Бин через который вы получите нужную операцию
    //Бин в который передадите http клиента
    //

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String id = context.getMergedJobDataMap().getString("operation");
        ScheduledOperationEntity scheduledOperation = this.service.getById(UUID.fromString(id));
        try {
            sendPost(OperationHttpPost.Builder.anOperationPost()
                    .withValue(scheduledOperation.getValue())
                    .withDescription(scheduledOperation.getDescription())
                    .withCurrency(scheduledOperation.getCurrency())
                    .withCategory(scheduledOperation.getCategory())
                    .withDate(scheduledOperation.getDt_create())
                    .build(), scheduledOperation.getAccount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendPost(OperationHttpPost operation, UUID account_uuid) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpPost request = new HttpPost("http://localhost:8080/account/" + account_uuid + "/operation");
            StringEntity params = new StringEntity(mapper.writeValueAsString(operation));
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().toString());
        } catch (Exception ex) {
        }


    }
}
