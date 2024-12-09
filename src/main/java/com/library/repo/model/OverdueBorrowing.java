package com.library.repo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class OverdueBorrowing {
    long EGN;
    long uniqueBookNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime borrowedTime;

    public long getEGN() {
        return EGN;
    }

    public long getUniqueBookNumber() {
        return uniqueBookNumber;
    }

    public LocalDateTime getBorrowedTime() {
        return borrowedTime;
    }

    public static final class OverdueBorrowingBuilder {
        private long EGN;
        private long uniqueBookNumber;
        private LocalDateTime borrowedTime;

        public OverdueBorrowingBuilder() {
        }

        public static OverdueBorrowingBuilder aOverdueBorrowing() {
            return new OverdueBorrowingBuilder();
        }

        public OverdueBorrowingBuilder withEGN(long EGN) {
            this.EGN = EGN;
            return this;
        }

        public OverdueBorrowingBuilder withUniqueBookNumber(long UBN) {
            this.uniqueBookNumber = UBN;
            return this;
        }

        public OverdueBorrowingBuilder withBorrowedTime(LocalDateTime borrowedTime) {
            this.borrowedTime = borrowedTime;
            return this;
        }

        public OverdueBorrowing build() {
            OverdueBorrowing overdueBorrowing = new OverdueBorrowing();
            overdueBorrowing.EGN = this.EGN;
            overdueBorrowing.uniqueBookNumber = this.uniqueBookNumber;
            overdueBorrowing.borrowedTime = this.borrowedTime;
            return overdueBorrowing;
        }
    }
}
