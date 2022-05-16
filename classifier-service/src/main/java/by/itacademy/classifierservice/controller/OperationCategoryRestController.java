package by.itacademy.classifierservice.controller;

import by.itacademy.classifierservice.model.Page;
import by.itacademy.classifierservice.model.dto.Currency;
import by.itacademy.classifierservice.model.dto.OperationCategory;
import by.itacademy.classifierservice.model.entity.OperationCategoryEntity;
import by.itacademy.classifierservice.services.impl.OperationCategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classifier/operation/category")
public class OperationCategoryRestController {

    private final OperationCategoryServiceImpl service;

    public OperationCategoryRestController(OperationCategoryServiceImpl service) {
        this.service = service;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCurrency(@RequestBody OperationCategory operationCategory) {
        this.service.add(this.service.convertToEntity(operationCategory));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<Page<OperationCategory>> getCurrencyPage(@RequestParam String size,
                                                          @RequestParam String page) {
        return ResponseEntity.ok(this.service.getPage(size,page));
    }
}
