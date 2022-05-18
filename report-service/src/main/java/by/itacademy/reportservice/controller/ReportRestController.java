package by.itacademy.reportservice.controller;

import by.itacademy.reportservice.controller.advice.ValidationError;
import by.itacademy.reportservice.model.entity.ReportEntity;
import by.itacademy.reportservice.services.ReportService;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportRestController {

    private final ReportService service;
    private final ConversionService conversionService;

    public ReportRestController(ReportService service, ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @RequestMapping(value = "/{type}",
            method = RequestMethod.POST)
    public String addReport(@RequestBody Map<String, Object> report,
                            @PathVariable String type) {
        return "account";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPageOfReport(@RequestParam String size,
                                  @RequestParam String page) {
        return "account";
    }


    @RequestMapping(value = "/account/{uuid}/export", method = RequestMethod.GET)
    public String downloadReport(@PathVariable UUID uuid) {
        return null;
    }

    @RequestMapping(value = "/account/{uuid}/export", method = RequestMethod.HEAD)
    public String checkAvailabilityReport(@PathVariable UUID uuid) {
        return "account";
    }
}
