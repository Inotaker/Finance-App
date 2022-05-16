package by.itacademy.accountservice.model.entity;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "operation", schema = "account_service")
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

    private boolean available;/**Для удаления*/

    public OperationEntity() {
    }

    public void setDt_update(long dt_update) {
        this.dt_update = dt_update;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setAccount(UUID account) {
        this.account = account;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    public long getDate() {
        return date;
    }

    public UUID getAccount() {
        return account;
    }

    public String getDescription() {
        return description;
    }

    public UUID getCategory() {
        return category;
    }

    public Integer getValue() {
        return value;
    }

    public UUID getCurrency() {
        return currency;
    }

    public boolean isAvailable() {
        return available;
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
        private boolean available;/**Для удаления*/

        private Builder() {
            long time = System.currentTimeMillis();
            this.dt_create = time;
            this.dt_update = time;
            this.uuid = UUID.randomUUID();
            this.available = true;
        }

        public static Builder anOperationEntity() {
            return new Builder();
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


        public OperationEntity build() {
            OperationEntity operationEntity = new OperationEntity();
            operationEntity.setDt_update(dt_update);
            operationEntity.setDate(date);
            operationEntity.setAccount(account);
            operationEntity.setDescription(description);
            operationEntity.setCategory(category);
            operationEntity.setValue(value);
            operationEntity.setCurrency(currency);
            operationEntity.uuid = this.uuid;
            operationEntity.dt_create = this.dt_create;
            operationEntity.available = this.available;
            return operationEntity;
        }
    }
}
