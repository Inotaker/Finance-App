package by.itacademy.reportservice.model.dto.params;

import java.util.List;
import java.util.UUID;

public class ReportParamByDate {
    List<UUID> accounts;
    long from;//$date
    long to;//$date
    List<UUID> categories;
}
