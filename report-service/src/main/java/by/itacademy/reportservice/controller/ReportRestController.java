package by.itacademy.reportservice.controller;

import by.itacademy.reportservice.model.dto.Report;
import by.itacademy.reportservice.model.entity.ReportEntity;
import by.itacademy.reportservice.services.ReportService;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

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
    public String addReport(@RequestBody Report report,
                            @PathVariable String type) {
        report.setType(type);
        this.service.add(this.conversionService.convert(report, ReportEntity.class));
        return "account";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getReport() {
        return "account";
    }


    @RequestMapping(value = "/account/{uuid}/export", method = RequestMethod.GET)
    public String downloadReport() {
        return null;
    }

    @RequestMapping(value = "/account/{uuid}/export", method = RequestMethod.HEAD)
    public String checkAvailabilityReport() {
        return "account";
    }
}
