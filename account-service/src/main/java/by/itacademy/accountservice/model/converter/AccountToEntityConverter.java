package by.itacademy.accountservice.model.converter;

import by.itacademy.accountservice.model.dto.Account;
import by.itacademy.accountservice.model.entity.AccountEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountToEntityConverter implements Converter<Account, AccountEntity> {
    @Override
    public AccountEntity convert(Account source) {
        return AccountEntity
                .Builder
                .anAccountEntity()
                .withBalance(source.getBalance())
                .withCurrency(source.getCurrency())
                .withDescription(source.getDescription())
                .withTitle(source.getTitle())
                .withType(source.getType())
                .withDt_create(source.getDt_create())
                .withDt_update(source.getDt_update())
                .withUuid(source.getUuid())
                .build();
    }
}
