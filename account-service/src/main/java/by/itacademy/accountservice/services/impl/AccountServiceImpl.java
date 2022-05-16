package by.itacademy.accountservice.services.impl;

import by.itacademy.accountservice.controller.advice.ValidationError;
import by.itacademy.accountservice.controller.advice.ValidationException;
import by.itacademy.accountservice.dao.api.IAccountStorage;
import by.itacademy.accountservice.model.dto.Account;
import by.itacademy.accountservice.model.dto.Page;
import by.itacademy.accountservice.model.entity.AccountEntity;
import by.itacademy.accountservice.model.enums.AccountType;
import by.itacademy.accountservice.services.AccountService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private ConversionService conversionService;
    private final IAccountStorage storage;

    public AccountServiceImpl(IAccountStorage storage, ConversionService conversionService) {
        this.storage = storage;
        this.conversionService = conversionService;
    }


    @Override
    public AccountEntity addAccount(AccountEntity entity) {
        List<ValidationError> errors = new ArrayList<>();
        int countError = 0;
        if (entity.getDescription().equals("")) {
            errors.add(new ValidationError("error", "поле description не может быть пустым"));
            countError++;
        }
        if (entity.getTitle().equals("")) {
            errors.add(new ValidationError("error", "поле title не может быть пустым"));
            countError++;
        }
        if (entity.getType().equals("")) {
            errors.add(new ValidationError("error", "поле type не может быть пустым"));
            countError++;
        }
        try {
            entity.setType(AccountType.valueOf(entity.getType()).toString());
        } catch (IllegalArgumentException e) {
            errors.add(new ValidationError("type_enum", "поле type не соответствую ни одному enum"));
        }
        if (countError > 0) throw new ValidationException("ошибка валидация аккаунта", errors);

        long time = System.currentTimeMillis();
        entity.setDt_create(time);
        entity.setDt_update(time);
        entity.setUuid(UUID.randomUUID());
        entity.setBalance(0);

        return this.storage.save(entity);
    }

    @Override
    public AccountEntity getById(UUID uuid) {
        return this.storage.findById(uuid).get();
    }


    @Override
    public AccountEntity editAccount(UUID uuid, AccountEntity accountEntity, long dt_update) {
        AccountEntity accountEntityAdd = getById(uuid);

        List<ValidationError> errors = new ArrayList<>();
        if (accountEntityAdd.getDt_update() != dt_update) throw new ValidationException("не совпадает dt_update");
        if (accountEntity.getDescription().equals("")) {
            errors.add(new ValidationError("error", "поле description не может быть пустым"));
        }
        if (accountEntity.getTitle().equals("")) {
            errors.add(new ValidationError("error", "поле title не может быть пустым"));
        }
        if (accountEntity.getType().equals("")) {
            errors.add(new ValidationError("error", "поле type не может быть пустым"));
        }
        if (errors.size() > 0) throw new ValidationException("ошибка валидация аккаунта", errors);

        accountEntityAdd.setTitle(accountEntity.getTitle());
        accountEntityAdd.setCurrency(accountEntity.getCurrency());
        accountEntityAdd.setDescription(accountEntity.getDescription());
        accountEntityAdd.setType(accountEntity.getType());
        accountEntityAdd.setDt_update(System.currentTimeMillis());
        storage.save(accountEntityAdd);/**сохранял через add, а там присваивается новый uuid*/
        return accountEntityAdd;
    }

    @Override
    public boolean editBalance(Integer balance, UUID accountId) {
        AccountEntity account = getById(accountId);
        account.setBalance((account.getBalance() + balance));
        editAccount(accountId, account, account.getDt_update());
        return true;
    }

    @Override
    public Page<Account> getPage(String size, String page) {
        Page<Account> accountPage = new Page<>();

        int p = Integer.parseInt(page) - 1;/**отсчет страниц идет с нуля*/
        int s = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(p, s);
        List<Account> accounts = new ArrayList<>();
        List<AccountEntity> accountEntities = this.storage.findAllBy(pageable);
        if (accountEntities.size() == 0) throw new ValidationException("нет счетов!");
        for (AccountEntity accountEntity : accountEntities) {
            accounts.add(convertToDto(accountEntity));
        }
        accountPage.setContent(accounts);
        if (!pageable.hasPrevious()) {
            accountPage.setFirst(true);
        }

        int totalElement = (int) this.storage.count();
        int totalPage = totalElement / s;

        if (totalElement % s > 0) {/**Если есть остаток после деления, значит есть неполная страница
         */
            totalPage++;
        }
        if (pageable.getPageSize() > accountEntities.size() || pageable.getPageSize() == totalElement) {
            accountPage.setLast(true);
        }
        accountPage.setTotal_pages(totalPage);
        accountPage.setTotal_element(totalElement);
        accountPage.setNumber_of_elements(accountEntities.size());
        accountPage.setSize(pageable.getPageSize());
        accountPage.setNumber(pageable.getPageNumber() + 1);/**отсчет страниц идет с нуля*/
        return accountPage;
    }

    @Override
    public AccountEntity convertToEntity(Account account) {
        return this.conversionService.convert(account, AccountEntity.class);
    }

    @Override
    public Account convertToDto(AccountEntity entity) {
        return this.conversionService.convert(entity, Account.class);
    }

}
