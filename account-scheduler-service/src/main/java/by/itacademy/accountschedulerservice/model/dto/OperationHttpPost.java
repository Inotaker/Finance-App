package by.itacademy.accountschedulerservice.model.dto;

import java.util.UUID;

public class OperationHttpPost {
    private long date;
    private String description;/**как связать акаунт из другой базы данных*/
    private long value;
    private UUID currency;//$uuid
    private UUID category;

    public OperationHttpPost() {
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    public static final class Builder {
        private long date;
        private String description;
        private long value;
        private UUID currency;//$uuid
        private UUID category;

        private Builder() {
        }

        public static Builder anOperationPost() {
            return new Builder();
        }

        public Builder withDate(long date) {
            this.date = date;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
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

        public Builder withCategory(UUID category) {
            this.category = category;
            return this;
        }

        public OperationHttpPost build() {
            OperationHttpPost operationHttpPost = new OperationHttpPost();
            operationHttpPost.setDate(date);
            operationHttpPost.setDescription(description);
            operationHttpPost.setValue(value);
            operationHttpPost.setCurrency(currency);
            operationHttpPost.setCategory(category);
            return operationHttpPost;
        }
    }
}
