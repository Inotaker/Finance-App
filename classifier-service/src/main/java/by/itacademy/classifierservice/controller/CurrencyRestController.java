package by.itacademy.classifierservice.controller;

import by.itacademy.classifierservice.model.Page;
import by.itacademy.classifierservice.model.dto.Currency;
import by.itacademy.classifierservice.services.impl.CurrencyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/classifier/currency")
public class CurrencyRestController {

    private final CurrencyServiceImpl service;

    public CurrencyRestController(CurrencyServiceImpl service) {
        this.service = service;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCurrency(@RequestBody Currency currency) {
        this.service.add(this.service.convertToEntity(currency));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Currency>> getCurrencyPage(@RequestParam String size,
                                                          @RequestParam String page) {
        return ResponseEntity.ok(this.service.getPage(size, page));
    }

    /**
     * Для проверок, на наличие валюты в базе
     */
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> currencyExists(@PathVariable UUID uuid) {
        if (this.service.isExists(uuid)) {
            return new ResponseEntity<>(HttpStatus.OK);/**FOUND - 302 status code*/
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);/**NOT FOUND - 404 status code
             CONFLICT - 409 status code
             NO CONTENT - 204 status code*/
        }
    }
}

