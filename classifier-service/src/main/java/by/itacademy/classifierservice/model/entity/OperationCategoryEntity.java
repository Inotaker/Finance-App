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

    public OperationCategoryEntity() {
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

    public String getTitle() {
        return title;
    }

    public void setDt_update(long dt_update) {
        this.dt_update = dt_update;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public static final class OperationCategoryEntityBuilder {
        private UUID uuid;
        private long dt_create;//$int64
        private long dt_update;//$int64
        private String title;//example Auto

        private OperationCategoryEntityBuilder() {
            long time = System.currentTimeMillis();
            this.uuid = UUID.randomUUID();
            this.dt_create = time;
            this.dt_update = time;
        }

        public static OperationCategoryEntityBuilder anOperationCategoryEntity() {
            return new OperationCategoryEntityBuilder();
        }

        public OperationCategoryEntityBuilder withUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public OperationCategoryEntityBuilder withDt_create(long dt_create) {
            this.dt_create = dt_create;
            return this;
        }

        public OperationCategoryEntityBuilder withDt_update(long dt_update) {
            this.dt_update = dt_update;
            return this;
        }

        public OperationCategoryEntityBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public OperationCategoryEntity build() {
            OperationCategoryEntity operationCategoryEntity = new OperationCategoryEntity();
            operationCategoryEntity.setDt_update(dt_update);
            operationCategoryEntity.setTitle(title);
            operationCategoryEntity.dt_create = this.dt_create;
            operationCategoryEntity.uuid = this.uuid;
            return operationCategoryEntity;
        }
    }
}
