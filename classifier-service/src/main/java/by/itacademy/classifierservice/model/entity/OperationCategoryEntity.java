package by.itacademy.classifierservice.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "operation_category", schema = "classifier_service")
public class OperationCategoryEntity {
    @Id
    private UUID uuid;
    private long dt_create;//$int64
    private long dt_update;//$int64

    private String title;//example Auto
    private String description;

    public OperationCategoryEntity() {
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
        private String title;//example Auto
        private String description;

        private Builder() {
        }

        public static Builder anOperationCategoryEntity() {
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

        public OperationCategoryEntity build() {
            OperationCategoryEntity operationCategoryEntity = new OperationCategoryEntity();
            operationCategoryEntity.setUuid(uuid);
            operationCategoryEntity.setDt_create(dt_create);
            operationCategoryEntity.setDt_update(dt_update);
            operationCategoryEntity.setTitle(title);
            operationCategoryEntity.setDescription(description);
            return operationCategoryEntity;
        }
    }
}
