package by.itacademy.accountschedulerservice.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class LocalDateTimeToDate implements Converter<LocalDateTime, Date> {
    @Override
    public Date convert(LocalDateTime source) {
        return Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
    }
}
