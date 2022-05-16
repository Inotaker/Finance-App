package by.itacademy.reportservice.model.dto;

import by.itacademy.reportservice.model.dto.params.ReportParam;

import java.util.UUID;

public class Report {

    private UUID uuid;
    private long dt_create;//$int64
    private long dt_update;//$int64

    private String status;//ENUM
    private String type;//ENUM
    /**
     * readOnly: true
     * example: Дата совершения операции: 01.01.2021 - 01.01.2022
     * Описание параметров отчёта в человеческом формате
     **/
    private String description;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Report() {
    }

    public static final class Builder {
        private UUID uuid;
        private long dt_create;//$int64
        private long dt_update;//$int64
        private String status;//ENUM
        private String type;//ENUM
        private String description;

        private Builder() {
        }

        public static Builder aReport() {
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

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Report build() {
            Report report = new Report();
            report.setUuid(uuid);
            report.setDt_create(dt_create);
            report.setDt_update(dt_update);
            report.setStatus(status);
            report.setType(type);
            report.setDescription(description);
            return report;
        }
    }
}
