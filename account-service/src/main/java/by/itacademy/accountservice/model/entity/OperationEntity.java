package by.itacademy.accountservice.model.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "operation", schema = "account_service")
@Transactional(readOnly = true)
public class OperationEntity {
    @Id
    private UUID uuid;

    private long dt_create;
    private long dt_update;
    private long date;

    private UUID account;

    private String description;
    private UUID category;
    private Integer value;
    private UUID currency;

    private boolean available; /**только для базы данных// доступна ли операция*/

    /**
     * Для удаления
     */

    public OperationEntity() {
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public UUID getAccount() {
        return account;
    }

    public void setAccount(UUID account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static final class Builder {
        private UUID uuid;
        private long dt_create;
        private long dt_update;
        private long date;
        private UUID account;
        private String description;
        private UUID category;
        private Integer value;
        private UUID currency;
        private boolean available;

        private Builder() {
        }

        public static Builder anOperationEntity() {
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

        public Builder withDate(long date) {
            this.date = date;
            return this;
        }

        public Builder withAccount(UUID account) {
            this.account = account;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withCategory(UUID category) {
            this.category = category;
            return this;
        }

        public Builder withValue(Integer value) {
            this.value = value;
            return this;
        }

        public Builder withCurrency(UUID currency) {
            this.currency = currency;
            return this;
        }

        public Builder withAvailable(boolean available) {
            this.available = available;
            return this;
        }

        public OperationEntity build() {
            OperationEntity operationEntity = new OperationEntity();
            operationEntity.setUuid(uuid);
            operationEntity.setDt_create(dt_create);
            operationEntity.setDt_update(dt_update);
            operationEntity.setDate(date);
            operationEntity.setAccount(account);
            operationEntity.setDescription(description);
            operationEntity.setCategory(category);
            operationEntity.setValue(value);
            operationEntity.setCurrency(currency);
            operationEntity.setAvailable(available);
            return operationEntity;
        }
    }
}
