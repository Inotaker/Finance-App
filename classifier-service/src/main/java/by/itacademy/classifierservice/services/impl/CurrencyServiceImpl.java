package by.itacademy.classifierservice.services.impl;

import by.itacademy.classifierservice.controller.advice.ValidationException;
import by.itacademy.classifierservice.dao.api.ICurrencyStorage;
import by.itacademy.classifierservice.model.Page;
import by.itacademy.classifierservice.model.dto.Currency;
import by.itacademy.classifierservice.model.entity.CurrencyEntity;
import by.itacademy.classifierservice.services.CurrencyService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final ConversionService conversionService;
    private final ICurrencyStorage storage;

    public CurrencyServiceImpl(ConversionService conversionService, ICurrencyStorage storage) {
        this.conversionService = conversionService;
        this.storage = storage;
    }

    @Override
    public CurrencyEntity add(CurrencyEntity currencyEntity) {

        if(this.storage.existsByTitle(currencyEntity.getTitle())){
            throw new ValidationException("такая валюта уже существует");
        }

        currencyEntity.setUuid(UUID.randomUUID());
        long time = System.currentTimeMillis();
        currencyEntity.setDt_create(time);
        currencyEntity.setDt_update(time);

        return this.storage.save(currencyEntity);
    }


    @Override
    public Page<Currency> getPage(String size, String page) {

        Page<Currency> currencyPage = new Page<>();

        int p = Integer.parseInt(page) - 1;/**отсчет страниц идет с нуля*/
        int s = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(p, s);
        List<CurrencyEntity> currencyEntities = this.storage.findAllBy(pageable);
        List<Currency> currencies = new ArrayList<>();
        for (CurrencyEntity currencyEntity : currencyEntities) {
            currencies.add(convertToDto(currencyEntity));
        }
        currencyPage.setContent(currencies);
        if (!pageable.hasPrevious()) {
            currencyPage.setFirst(true);
        }

        int totalElement = this.storage.findAll().size();
        int totalPage = totalElement / s;

        if (totalElement % s > 0) {/**Если есть остаток после деления, значит есть неполная страница*/
            totalPage++;
        }
        if (pageable.getPageSize() > currencyEntities.size() || pageable.getPageSize() == totalElement) {
            currencyPage.setLast(true);
        }
        currencyPage.setTotal_pages(totalPage);
        currencyPage.setTotal_element(totalElement);
        currencyPage.setNumber_of_elements(currencyEntities.size());
        currencyPage.setSize(pageable.getPageSize());
        currencyPage.setNumber(pageable.getPageNumber() + 1);/**отсчет страниц идет с нуля*/
        return currencyPage;
    }

    @Override
    public Currency convertToDto(CurrencyEntity currencyEntity) {
        return this.conversionService.convert(currencyEntity, Currency.class);
    }

    @Override
    public CurrencyEntity convertToEntity(Currency currency) {
        return this.conversionService.convert(currency, CurrencyEntity.class);
    }
}
