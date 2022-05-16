package by.itacademy.reportservice.model.converter;

import by.itacademy.reportservice.model.dto.Report;
import by.itacademy.reportservice.model.entity.ReportEntity;
import org.springframework.core.convert.converter.Converter;

public class ReportToEntityConverter implements Converter<Report, ReportEntity> {
    @Override
    public ReportEntity convert(Report source) {
        return null;
    }
}
