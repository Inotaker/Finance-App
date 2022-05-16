package by.itacademy.classifierservice.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "currency", schema = "classifier_service")
public class CurrencyEntity {
    @Id
    private UUID uuid;
    private long dt_create;//$int64
    private long dt_update;//$int64

    private String title;//example USD
    private String description;

    public CurrencyEntity() {
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public UUID getUuid() {
        return uuid;
    }

    public long getDt_create() {
        return dt_create;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static final class Builder {
        private UUID uuid;
        private long dt_create;//$int64
        private long dt_update;//$int64
        private String title;//example USD
        private String description;

        private Builder() {
        }

        public static Builder aCurrencyEntity() {
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

        public CurrencyEntity build() {
            CurrencyEntity currencyEntity = new CurrencyEntity();
            currencyEntity.setDt_update(dt_update);
            currencyEntity.setTitle(title);
            currencyEntity.setDescription(description);
            currencyEntity.uuid = this.uuid;
            currencyEntity.dt_create = this.dt_create;
            return currencyEntity;
        }
    }
}
