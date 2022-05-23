package by.itacademy.reportservice.model.converter;

import by.itacademy.reportservice.model.dto.Report;
import by.itacademy.reportservice.model.dto.params.param.by.ReportParamByDate;
import by.itacademy.reportservice.model.entity.ReportEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReportToEntityConverter implements Converter<Report, ReportEntity> {
    @Override
    public ReportEntity convert(Report source) {
        return ReportEntity.Builder.aReportEntity()
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
