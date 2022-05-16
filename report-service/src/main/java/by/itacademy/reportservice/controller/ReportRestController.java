package by.itacademy.reportservice.controller;

import by.itacademy.reportservice.model.dto.Report;
import by.itacademy.reportservice.model.dto.enums.ReportType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportRestController {

    @RequestMapping(value = "/{type}",
            method = RequestMethod.POST)
    public String addReport(@RequestBody Report report,
                            @PathVariable String type) {
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
