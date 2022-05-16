package by.itacademy.accountschedulerservice.model.dto;

import java.util.UUID;

public class OperationPost {
    private long date;
    private UUID account;//$uuid
    private String description;/**как связать акаунт из другой базы данных*/
    private long value;
    private UUID currency;//$uuid
    private UUID category;

    public OperationPost() {
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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
        private UUID account;//$uuid
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

        public Builder withAccount(UUID account) {
            this.account = account;
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

        public OperationPost build() {
            OperationPost operationPost = new OperationPost();
            operationPost.setDate(date);
            operationPost.setAccount(account);
            operationPost.setDescription(description);
            operationPost.setValue(value);
            operationPost.setCurrency(currency);
            operationPost.setCategory(category);
            return operationPost;
        }
    }
}
