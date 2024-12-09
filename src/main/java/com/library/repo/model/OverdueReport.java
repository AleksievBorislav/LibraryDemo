package com.library.repo.model;

import java.time.LocalDateTime;
import java.util.List;

public class OverdueReport {
    long reportId;
    long overdueBorrowings;
    List<OverdueBorrowing> overdueBorrowingList;
    LocalDateTime reportDate;

    public long getReportId() {
        return reportId;
    }

    public long getOverdueBorrowings() {
        return overdueBorrowings;
    }

    public List<OverdueBorrowing> getOverdueBorrowingList() {
        return overdueBorrowingList;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public static final class OverdueReportBuilder {
        private long reportId;
        private long overdueBorrowings;
        private List<OverdueBorrowing> overdueBorrowingList;
        private LocalDateTime reportDate;

        public OverdueReportBuilder() {
        }

        public static OverdueReportBuilder anOverdueReport() {
            return new OverdueReportBuilder();
        }

        public OverdueReportBuilder withId(long id) {
            this.reportId = id;
            return this;
        }

        public OverdueReportBuilder withOverdueBorrowings(long overdueBorrowings) {
            this.overdueBorrowings = overdueBorrowings;
            return this;
        }

        public OverdueReportBuilder withOverdueBorrowingList(List<OverdueBorrowing> overdueBorrowingList) {
            this.overdueBorrowingList = overdueBorrowingList;
            return this;
        }

        public OverdueReportBuilder withReportDate(LocalDateTime reportDate) {
            this.reportDate = reportDate;
            return this;
        }

        public OverdueReport build() {
            OverdueReport overdueReport = new OverdueReport();
            overdueReport.reportId = this.reportId;
            overdueReport.overdueBorrowingList = this.overdueBorrowingList;
            overdueReport.reportDate = this.reportDate;
            overdueReport.overdueBorrowings = this.overdueBorrowings;
            return overdueReport;
        }
    }
}
