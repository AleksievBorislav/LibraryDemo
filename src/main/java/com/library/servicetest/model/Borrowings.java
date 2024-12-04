package com.library.servicetest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Borrowings {
    long uniqueBookNumber;
    long EGN;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime borrowTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime returnedTime;

    boolean isReturned;

    public long getUniqueBookNumber() {
        return uniqueBookNumber;
    }

    public long getEGN() {
        return EGN;
    }

    public LocalDateTime getBorrowTime() {
        return borrowTime;
    }

    public LocalDateTime getReturnedTime() {
        return returnedTime;
    }

    public boolean isReturned() {
        return isReturned;
    }


    public static final class BorrowingsBuilder {
        private long uniqueBookNumber;
        private long EGN;
        private LocalDateTime borrowTime;
        private LocalDateTime returnedTime;
        private boolean isReturned;

        public BorrowingsBuilder() {
        }

        public static BorrowingsBuilder aBorrowings() {
            return new BorrowingsBuilder();
        }

        public BorrowingsBuilder withUniqueBookNumber(long uniqueBookNumber) {
            this.uniqueBookNumber = uniqueBookNumber;
            return this;
        }

        public BorrowingsBuilder withEGN(long EGN) {
            this.EGN = EGN;
            return this;
        }

        public BorrowingsBuilder withBorrowTime(LocalDateTime borrowTime) {
            this.borrowTime = borrowTime;
            return this;
        }

        public BorrowingsBuilder withReturnedTime(LocalDateTime returnedTime) {
            this.returnedTime = returnedTime;
            return this;
        }

        public BorrowingsBuilder withIsReturned(boolean isReturned) {
            this.isReturned = isReturned;
            return this;
        }

        public Borrowings build() {
            Borrowings borrowings = new Borrowings();
            borrowings.uniqueBookNumber = this.uniqueBookNumber;
            borrowings.borrowTime = this.borrowTime;
            borrowings.returnedTime = this.returnedTime;
            borrowings.EGN = this.EGN;
            borrowings.isReturned = this.isReturned;
            return borrowings;
        }
    }
}
