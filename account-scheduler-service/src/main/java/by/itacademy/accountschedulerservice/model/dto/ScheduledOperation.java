package by.itacademy.accountschedulerservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ScheduledOperation {
    private UUID uuid;

    private long dt_create;//$int64
    private long dt_update;//$int64

    @JsonProperty("schedule")
    private Schedule schedule;
    @JsonProperty("operation")
    private Operation operation;


    public ScheduledOperation() {
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public static final class Builder {
        private UUID uuid;
        private long dt_create;//$int64
        private long dt_update;//$int64
        private Schedule schedule;
        private Operation operation;

        private Builder() {
        }

        public static Builder aScheduledOperation() {
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

        public Builder withSchedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public Builder withOperation(Operation operation) {
            this.operation = operation;
            return this;
        }

        public ScheduledOperation build() {
            ScheduledOperation scheduledOperation = new ScheduledOperation();
            scheduledOperation.setUuid(uuid);
            scheduledOperation.setDt_create(dt_create);
            scheduledOperation.setDt_update(dt_update);
            scheduledOperation.setSchedule(schedule);
            scheduledOperation.setOperation(operation);
            return scheduledOperation;
        }
    }
}
