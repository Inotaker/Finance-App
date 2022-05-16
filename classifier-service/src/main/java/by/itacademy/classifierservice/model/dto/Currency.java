package by.itacademy.classifierservice.model.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

public class Currency {
    private UUID uuid;
    private long dt_create;//$int64
    private long dt_update;//$int64

    private String title;//example USD
    private String description;

    public Currency() {
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

    public static final class Builder {
        private UUID uuid;
        private long dt_create;//$int64
        private long dt_update;//$int64
        private String title;//example USD
        private String description;

        private Builder() {
        }

        public static Builder aCurrency() {
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

        public Currency build() {
            Currency currency = new Currency();
            currency.setUuid(uuid);
            currency.setDt_create(dt_create);
            currency.setDt_update(dt_update);
            currency.setTitle(title);
            currency.setDescription(description);
            return currency;
        }
    }
}
