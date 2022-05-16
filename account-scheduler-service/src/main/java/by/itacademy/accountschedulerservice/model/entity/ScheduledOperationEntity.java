package by.itacademy.accountschedulerservice.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "scheduled_operation", schema = "scheduler_service")
public class ScheduledOperationEntity {
    @Id
    private UUID uuid;

    private long dt_create;//$int64
    private long dt_update;//$int64

    private UUID account;//$uuid
    private String description;
    private UUID category;
    private long value;
    private UUID currency;//$uuid

    private long start_time;//$int64
    private long stop_time;//$int64
    private int interval;//$int64
    private String time_unit;

    public ScheduledOperationEntity() {
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

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getStop_time() {
        return stop_time;
    }

    public void setStop_time(long stop_time) {
        this.stop_time = stop_time;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getTime_unit() {
        return time_unit;
    }

    public void setTime_unit(String time_unit) {
        this.time_unit = time_unit;
    }

    public static final class Builder {
        private UUID uuid;
        private long dt_create;//$int64
        private long dt_update;//$int64
        private UUID account;//$uuid
        private String description;
        private UUID category;
        private long value;
        private UUID currency;//$uuid
        private long start_time;//$int64
        private long stop_time;//$int64
        private int interval;//$int64
        private String time_unit;

        private Builder() {
        }

        public static Builder aScheduledOperationEntity() {
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

        public Builder withValue(long value) {
            this.value = value;
            return this;
        }

        public Builder withCurrency(UUID currency) {
            this.currency = currency;
            return this;
        }

        public Builder withStart_time(long start_time) {
            this.start_time = start_time;
            return this;
        }

        public Builder withStop_time(long stop_time) {
            this.stop_time = stop_time;
            return this;
        }

        public Builder withInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public Builder withTime_unit(String time_unit) {
            this.time_unit = time_unit;
            return this;
        }

        public ScheduledOperationEntity build() {
            ScheduledOperationEntity scheduledOperationEntity = new ScheduledOperationEntity();
            scheduledOperationEntity.setUuid(uuid);
            scheduledOperationEntity.setDt_create(dt_create);
            scheduledOperationEntity.setDt_update(dt_update);
            scheduledOperationEntity.setAccount(account);
            scheduledOperationEntity.setDescription(description);
            scheduledOperationEntity.setCategory(category);
            scheduledOperationEntity.setValue(value);
            scheduledOperationEntity.setCurrency(currency);
            scheduledOperationEntity.setStart_time(start_time);
            scheduledOperationEntity.setStop_time(stop_time);
            scheduledOperationEntity.setInterval(interval);
            scheduledOperationEntity.setTime_unit(time_unit);
            return scheduledOperationEntity;
        }
    }
}
