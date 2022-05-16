package by.itacademy.accountservice.model.entity;

import by.itacademy.accountservice.model.dto.Account;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "account", schema = "account_service")
public class AccountEntity {
    @Id
    private UUID uuid;

    private long dt_create;
    private long dt_update;

    private String title;
    private String description;
    private Integer balance;
    private String type;
    private UUID currency;

    public AccountEntity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public long getDt_create() {
        return dt_create;
    }

    public long getDt_update() {
        return dt_update;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }

    public void setDt_update(long dt_update) {
        this.dt_update = dt_update;
    }

    public static final class Builder {
        private UUID uuid;
        private long dt_create;
        private long dt_update;
        private String title;
        private String description;
        private Integer balance;
        private String type;
        private UUID currency;

        private Builder() {
            long time = System.currentTimeMillis();
            this.dt_create = time;
            this.dt_update = time;
            this.uuid = UUID.randomUUID();
            this.balance = 0;
        }

        public static Builder anAccountEntity() {
            return new Builder();
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withBalance(Integer balance) {
            this.balance = balance;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withCurrency(UUID currency) {
            this.currency = currency;
            return this;
        }

        public AccountEntity build() {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setTitle(title);
            accountEntity.setDescription(description);
            accountEntity.setBalance(balance);
            accountEntity.setType(type);
            accountEntity.setCurrency(currency);
            accountEntity.dt_update = this.dt_update;
            accountEntity.uuid = this.uuid;
            accountEntity.dt_create = this.dt_create;
            return accountEntity;
        }
    }
}
