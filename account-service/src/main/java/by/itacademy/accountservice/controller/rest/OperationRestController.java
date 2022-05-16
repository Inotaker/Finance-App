package by.itacademy.accountservice.controller.rest;

import by.itacademy.accountservice.model.dto.Operation;
import by.itacademy.accountservice.model.dto.Page;
import by.itacademy.accountservice.services.impl.OperationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account/{uuid}/operation")
public class OperationRestController {

    private final OperationServiceImpl service;

    public OperationRestController(OperationServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addOperation(@RequestBody Operation operation,
                                          @PathVariable UUID uuid) {
        this.service.addOperation(this.service.convertToEntity(operation), uuid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Operation>> getPageOperation(@RequestParam String page,
                                                            @RequestParam String size,
                                                            @PathVariable UUID uuid) {
        return ResponseEntity.ok(service.getPage(size, page, uuid));
    }


    @RequestMapping(value = "/{uuid_operation}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> editInfoOperation(@PathVariable String dt_update,
                                               @PathVariable UUID uuid,
                                               @PathVariable UUID uuid_operation,
                                               @RequestBody Operation operation) {
        this.service.editOperation(uuid, uuid_operation, dt_update, this.service.convertToEntity(operation));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{uuid_operation}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOperation(@PathVariable String dt_update,
                                             @PathVariable UUID uuid_operation,
                                             @PathVariable UUID uuid) {
        service.delete(uuid, uuid_operation, dt_update);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
