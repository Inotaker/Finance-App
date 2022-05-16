package by.itacademy.reportservice.model.converter;

import by.itacademy.reportservice.model.dto.Report;
import by.itacademy.reportservice.model.entity.ReportEntity;
import org.springframework.core.convert.converter.Converter;

public class ReportToDtoConverter implements Converter<ReportEntity, Report> {
    @Override
    public Report convert(ReportEntity source) {
        return null;
    }
}
