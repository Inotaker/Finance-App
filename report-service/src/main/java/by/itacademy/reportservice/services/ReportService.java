package by.itacademy.reportservice.services;

import by.itacademy.reportservice.dao.api.IReportStorage;
import by.itacademy.reportservice.model.entity.ReportEntity;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final IReportStorage storage;

    public ReportService(IReportStorage storage) {
        this.storage = storage;
    }

    public ReportEntity add(ReportEntity entity) {
        return entity;
    }
}
