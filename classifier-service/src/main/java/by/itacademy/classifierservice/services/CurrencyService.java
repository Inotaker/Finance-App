package by.itacademy.classifierservice.services;

import by.itacademy.classifierservice.model.Page;
import by.itacademy.classifierservice.model.dto.Currency;
import by.itacademy.classifierservice.model.entity.CurrencyEntity;

public interface CurrencyService {
    CurrencyEntity add(CurrencyEntity currencyEntity);

    Page<Currency> getPage(String size, String page);

    Currency convertToDto(CurrencyEntity currencyEntity);

    CurrencyEntity convertToEntity(Currency currency);
}
