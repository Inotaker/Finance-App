package by.itacademy.classifierservice.model.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

public class OperationCategory {

    private UUID uuid;
    private long dt_create;//$int64
    private long dt_update;//$int64

    private String title;//example Auto

    public OperationCategory() {
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

    public static final class OperationCategoryBuilder {
        private UUID uuid;
        private long dt_create;//$int64
        private long dt_update;//$int64
        private String title;//example Auto

        private OperationCategoryBuilder() {
        }

        public static OperationCategoryBuilder anOperationCategory() {
            return new OperationCategoryBuilder();
        }

        public OperationCategoryBuilder withUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public OperationCategoryBuilder withDt_create(long dt_create) {
            this.dt_create = dt_create;
            return this;
        }

        public OperationCategoryBuilder withDt_update(long dt_update) {
            this.dt_update = dt_update;
            return this;
        }

        public OperationCategoryBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public OperationCategory build() {
            OperationCategory operationCategory = new OperationCategory();
            operationCategory.setDt_update(dt_update);
            operationCategory.setTitle(title);
            operationCategory.dt_create = this.dt_create;
            operationCategory.uuid = this.uuid;
            return operationCategory;
        }
    }
}
