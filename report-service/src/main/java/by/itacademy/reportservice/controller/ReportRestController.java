package by.itacademy.reportservice.controller;

import by.itacademy.reportservice.model.dto.Report;
import by.itacademy.reportservice.model.dto.enums.ReportType;
import by.itacademy.reportservice.model.dto.params.ReportParamBalance;
import by.itacademy.reportservice.model.dto.params.ReportParamByCategory;
import by.itacademy.reportservice.model.dto.params.ReportParamByDate;
import by.itacademy.reportservice.model.entity.ReportEntity;
import by.itacademy.reportservice.services.ReportService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
        List<UUID> accounts = new ArrayList<>();
        long from = 0;
        long to = 0;
        List<UUID> categories = new ArrayList<>();

        for (Map.Entry<String, Object> entry : report.entrySet()) {
            if (entry.getKey().equals("accounts")) {
                accounts = (List<UUID>) entry.getValue();
            }
            if (entry.getKey().equals("from")) {
                from = Long.parseLong((String) entry.getValue());
            }
            if (entry.getKey().equals("to")) {
                to = Long.parseLong((String) entry.getValue());
            }
            if (entry.getKey().equals("categories")) {
                categories = (List<UUID>) entry.getValue();
            }
        }
        switch (ReportType.valueOf(type)) {
            case BY_CATEGORY:
                ReportParamByCategory reportPBC = new ReportParamByCategory();
                reportPBC.setCategories(categories);
                reportPBC.setFrom(from);
                reportPBC.setTo(to);
                reportPBC.setAccounts(accounts);
                return new ResponseEntity<>(HttpStatus.CREATED);
            case BALANCE:
                ReportParamBalance reportPB = new ReportParamBalance();
                reportPB.setAccounts(accounts);
                return new ResponseEntity<>(HttpStatus.CREATED);
            case BY_DATE:
                ReportParamByDate reportPBD = new ReportParamByDate();
                reportPBD.setCategories(categories);
                reportPBD.setFrom(from);
                reportPBD.setTo(to);
                reportPBD.setAccounts(accounts);
                this.service.add(new Report<ReportParamByDate>.Builder().withT(reportPBD).build());
                return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String getPageOfReport(@RequestParam String size,
                                  @RequestParam String page) {
        return "account";
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
