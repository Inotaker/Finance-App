package by.itacademy.accountschedulerservice.controller;

import by.itacademy.accountschedulerservice.model.Page;
import by.itacademy.accountschedulerservice.model.dto.ScheduledOperation;
import by.itacademy.accountschedulerservice.services.impl.ScheduledOperationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/sсheduler/operation")
public class ScheduleRestController {

    private final ScheduledOperationServiceImpl service;

    public ScheduleRestController(ScheduledOperationServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addSchedule(@RequestBody ScheduledOperation scheduledOperation) {
        service.add(service.convertToEntity(scheduledOperation));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ScheduledOperation>> getSchedulePage(@RequestParam String size,
                                                                    @RequestParam String page) {
        return ResponseEntity.ok(service.getPage(size, page));
    }

    @RequestMapping(value = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> editInfoSchedule(@RequestBody ScheduledOperation scheduledOperation,
                                              @PathVariable UUID uuid,
                                              @PathVariable long dt_update) {
        this.service.editScheduledOperation(uuid, scheduledOperation, dt_update);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
