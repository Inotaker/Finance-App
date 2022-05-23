package by.itacademy.reportservice.model.converter;

import by.itacademy.reportservice.model.dto.Report;
import by.itacademy.reportservice.model.entity.ReportEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReportToDtoConverter implements Converter<ReportEntity, Report> {
    @Override
    public Report convert(ReportEntity source) {
        return Report.Builder.aReport()
                .withDt_create(source.getDt_create())
                .withDt_update(source.getDt_update())
                .withUuid(source.getUuid())
                .withDescription(source.getDescription())
                .withParams(source.getParams())
                .withType(source.getType())
                .withStatus(source.getStatus())
                .build();
    }
}
