package by.itacademy.reportservice.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class LongToLocalDateTime implements Converter<Long, LocalDateTime> {
    @Override
    public LocalDateTime convert(Long source) {
        return Instant.ofEpochMilli(source).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
