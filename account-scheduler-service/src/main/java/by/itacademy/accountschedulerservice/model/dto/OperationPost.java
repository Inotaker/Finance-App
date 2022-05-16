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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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

        public static OperationPost.Builder anOperation() {
            return new OperationPost.Builder();
        }

        public OperationPost.Builder withAccount(UUID account) {
            this.account = account;
            return this;
        }

        public OperationPost.Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public OperationPost.Builder withValue(long value) {
            this.value = value;
            return this;
        }

        public OperationPost.Builder withCurrency(UUID currency) {
            this.currency = currency;
            return this;
        }

        public OperationPost.Builder withCategory(UUID category) {
            this.category = category;
            return this;
        }

        public OperationPost build() {
            OperationPost operation = new OperationPost();
            operation.setAccount(account);
            operation.setDescription(description);
            operation.setValue(value);
            operation.setCurrency(currency);
            operation.setCategory(category);
            return operation;
        }

        public  OperationPost.Builder withDate(long date) {
            this.date =  date;
            return this;
        }
    }
}
