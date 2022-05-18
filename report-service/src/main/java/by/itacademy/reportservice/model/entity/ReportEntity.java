package by.itacademy.reportservice.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "report", schema = "report_service")
public class ReportEntity {
    @Id
    private UUID uuid;
    private LocalDateTime dt_create;//$int64
    private LocalDateTime dt_update;//$int64

    private String status;//ENUM
    private String type;//ENUM
    private String description;

    public ReportEntity() {
    }

    /** readOnly: true
     example: Дата совершения операции: 01.01.2021 - 01.01.2022
     Описание параметров отчёта в человеческом формате **/
    //params oneOf


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }

    public LocalDateTime getDt_update() {
        return dt_update;
    }

    public void setDt_update(LocalDateTime dt_update) {
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

    public static final class Builder {
        private UUID uuid;
        private LocalDateTime dt_create;//$int64
        private LocalDateTime dt_update;//$int64
        private String status;//ENUM
        private String type;//ENUM
        private String description;

        private Builder() {
        }

        public static Builder aReportEntity() {
            return new Builder();
        }

        public Builder withUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder withDt_create(LocalDateTime dt_create) {
            this.dt_create = dt_create;
            return this;
        }

        public Builder withDt_update(LocalDateTime dt_update) {
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

        public ReportEntity build() {
            ReportEntity reportEntity = new ReportEntity();
            reportEntity.setUuid(uuid);
            reportEntity.setDt_create(dt_create);
            reportEntity.setDt_update(dt_update);
            reportEntity.setStatus(status);
            reportEntity.setType(type);
            reportEntity.setDescription(description);
            return reportEntity;
        }
    }
}
