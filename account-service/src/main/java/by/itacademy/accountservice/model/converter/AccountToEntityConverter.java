package by.itacademy.accountservice.model.converter;

import by.itacademy.accountservice.model.dto.Account;
import by.itacademy.accountservice.model.entity.AccountEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountToEntityConverter implements Converter<Account, AccountEntity> {
    @Override
    public AccountEntity convert(Account source) {
        AccountEntity account = AccountEntity
                .Builder
                .anAccountEntity()
                .withCurrency(source.getCurrency())
                .withDescription(source.getDescription())
                .withTitle(source.getTitle())
                .withType(source.getType())
                .build();
        return account;
    }
}
