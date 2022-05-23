package by.itacademy.reportservice.controller;

import by.itacademy.reportservice.model.dto.Page;
import by.itacademy.reportservice.model.dto.Report;
import by.itacademy.reportservice.model.entity.ReportEntity;
import by.itacademy.reportservice.services.ReportService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/report")
public class ReportRestController {

    private final ReportService service;
    private final ConversionService conversionService;

    public ReportRestController(ReportService service, ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @RequestMapping(value = {"/{type}"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> add(@RequestBody Map<String, Object> report,
                                 @PathVariable(name = "type") String type) {
        this.service.add(this.conversionService.convert(this.service.transformReport(report, type), ReportEntity.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Report>> getPageOfReport(@RequestParam String size,
                                                        @RequestParam String page) {
        return ResponseEntity.ok(this.service.getPage(size, page));
    }

    @ResponseBody
    @RequestMapping(value = "/account/{uuid}/export", method = RequestMethod.GET)
    public String downloadReport(@PathVariable UUID uuid) {
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/account/{uuid}/export", method = RequestMethod.HEAD)
    public String checkAvailabilityReport(@PathVariable UUID uuid) {
        return "account";
    }
}
