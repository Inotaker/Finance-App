package by.itacademy.accountservice.services.api;

import by.itacademy.accountservice.model.dto.Page;
import by.itacademy.accountservice.model.dto.Account;
import by.itacademy.accountservice.model.entity.AccountEntity;

import java.util.UUID;

public interface AccountService {
    AccountEntity addAccount(AccountEntity entity);

    AccountEntity getById(UUID uuid);

    AccountEntity editAccount(UUID uuid, AccountEntity accountEntity, long dt_update);

    boolean editBalance(Integer balance, UUID accountId);

    Page<Account> getPage(String size, String page);

    AccountEntity convertToEntity(Account account);

    Account convertToDto(AccountEntity entity);
}
