package by.itacademy.classifierservice.model.converter;

import by.itacademy.classifierservice.model.dto.Currency;
import by.itacademy.classifierservice.model.entity.CurrencyEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyToEntityConverter implements Converter<Currency, CurrencyEntity> {
    @Override
    public CurrencyEntity convert(Currency source) {
        CurrencyEntity currency = CurrencyEntity
                .Builder.aCurrencyEntity()
                .withTitle(source.getTitle())
                .withDt_create(source.getDt_create())
                .withDt_update(source.getDt_update())
                .withUuid(source.getUuid())
                .withDescription(source.getDescription())
                .build();
        return currency;
    }
}
