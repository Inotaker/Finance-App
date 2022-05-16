package by.itacademy.reportservice.model.dto.params;

import java.util.List;
import java.util.UUID;

public class ReportParam {
    List<UUID> accounts;

    public List<UUID> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UUID> accounts) {
        this.accounts = accounts;
    }

    public ReportParam() {
    }

    public static final class Builder {
        List<UUID> accounts;

        private Builder() {
        }

        public static Builder aReportParam() {
            return new Builder();
        }

        public Builder withAccounts(List<UUID> accounts) {
            this.accounts = accounts;
            return this;
        }

        public ReportParam build() {
            ReportParam reportParam = new ReportParam();
            reportParam.setAccounts(accounts);
            return reportParam;
        }
    }
}
