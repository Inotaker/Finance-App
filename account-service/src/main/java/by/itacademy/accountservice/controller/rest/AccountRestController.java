package by.itacademy.accountservice.controller.rest;

import by.itacademy.accountservice.model.dto.Account;
import by.itacademy.accountservice.model.dto.Page;
import by.itacademy.accountservice.services.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountRestController {
    private final AccountServiceImpl service;

    public AccountRestController(AccountServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        this.service.addAccount(this.service.convertToEntity(account));
        return new ResponseEntity<>(HttpStatus.CREATED); /**Сделать проверки на null и ошибки 400**/
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Account>> getPageOfAccount(@RequestParam String page,
                                                          @RequestParam String size) {
        return ResponseEntity.ok(service.getPage(size, page));
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Account> getInfoAboutAccount(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(this.service.convertToDto(this.service.getById(uuid)));
    }


    @RequestMapping(value = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> editInfoAboutAccount(@PathVariable String dt_update,
                                                  @PathVariable UUID uuid,
                                                  @RequestBody Account account) {
        service.editAccount(uuid, this.service.convertToEntity(account), Long.parseLong(dt_update));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
