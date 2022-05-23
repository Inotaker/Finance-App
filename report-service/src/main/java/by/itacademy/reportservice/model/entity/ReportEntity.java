package by.itacademy.reportservice.model.entity;

import by.itacademy.reportservice.dao.MapToStringConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "report", schema = "report_service")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ReportEntity {
    @Id
    private UUID uuid;
    private long dt_create;//$int64
    private long dt_update;//$int64

    private String status;//ENUM
    private String type;//ENUM
    private String description;
//
//    @Convert(converter = MapToStringConverter.class)
    @Type(type = "json")
    private Map<String, Object> params;
    //    @Type(type = "json")
//    private Map<String, String> accounts;
//    private Collection<String> accounts;
    //    @Type(type = "json")
//    private Map<String, String> categories;
//    private Collection<String> categories;
//    @Column(name = "\"from\"")
//    private long from;
//    @Column(name = "\"to\"")
//    private long to;

    public ReportEntity() {
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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public static final class Builder {
        private UUID uuid;
        private long dt_create;//$int64
        private long dt_update;//$int64
        private String status;//ENUM
        private String type;//ENUM
        private String description;
        private Map<String, Object> params;

        private Builder() {
        }

        public static Builder aReportEntity() {
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

        public Builder withParams(Map<String, Object> params) {
            this.params = params;
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
            reportEntity.setParams(params);
            return reportEntity;
        }
    }
    /** readOnly: true
     example: Дата совершения операции: 01.01.2021 - 01.01.2022
     Описание параметров отчёта в человеческом формате **/
}
