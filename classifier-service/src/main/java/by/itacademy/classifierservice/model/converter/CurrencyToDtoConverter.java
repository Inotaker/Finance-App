package by.itacademy.classifierservice.model.converter;

import by.itacademy.classifierservice.model.dto.Currency;
import by.itacademy.classifierservice.model.entity.CurrencyEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyToDtoConverter implements Converter<CurrencyEntity,Currency> {
    @Override
    public Currency convert(CurrencyEntity source) {
        Currency currency  = Currency.Builder
                .aCurrency()
                .withTitle(source.getTitle())
                .withDt_create(source.getDt_create())
                .withDt_update(source.getDt_update())
                .withUuid(source.getUuid())
                .withDescription(source.getDescription())
                .build();
        return currency;
    }
}
