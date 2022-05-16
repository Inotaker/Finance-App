package by.itacademy.accountservice.model.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "account", schema = "account_service")
@Transactional(readOnly = true)
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public long getDt_create() {
        return dt_create;
    }

    public void setDt_create(long dt_create) {
        this.dt_create = dt_create;
    }

    public long getDt_update() {
        return dt_update;
    }

    public void setDt_update(long dt_update) {
        this.dt_update = dt_update;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
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
        }

        public static Builder anAccountEntity() {
            return new Builder();
        }

        public Builder withUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder withDt_create(long dt_create) {
            this.dt_create = dt_create;
            return this;
        }

        public Builder withDt_update(long dt_update) {
            this.dt_update = dt_update;
            return this;
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
            accountEntity.setUuid(uuid);
            accountEntity.setDt_create(dt_create);
            accountEntity.setDt_update(dt_update);
            accountEntity.setTitle(title);
            accountEntity.setDescription(description);
            accountEntity.setBalance(balance);
            accountEntity.setType(type);
            accountEntity.setCurrency(currency);
            return accountEntity;
        }
    }
}
