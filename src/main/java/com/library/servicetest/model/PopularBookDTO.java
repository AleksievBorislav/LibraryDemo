package com.library.servicetest.model;

import java.io.Serializable;

public class PopularBookDTO implements Serializable {
    long uniqueBookNumber;
    int borrowingsCount;
    String title;
    String author;

    public String getTitle() {
        return title;
    }

    public int getBorrowingsCount() {
        return borrowingsCount;
    }

    public String getAuthor() {
        return author;
    }

    public long getUniqueBookNumber() {
        return uniqueBookNumber;
    }

    public static final class BookBuilder {
        private int borrowingsCount;
        private long uniqueBookNumber;
        private String title;
        private String author;

        public BookBuilder() {
        }

        public static BookBuilder aBook() {
            return new BookBuilder();
        }

        public BookBuilder withBookId(int bookId) {
            this.borrowingsCount = bookId;
            return this;
        }

        public BookBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder withUniqueBookNumber(long uniqueBookNumber) {
            this.uniqueBookNumber = uniqueBookNumber;
            return this;
        }

        public PopularBookDTO build() {
            PopularBookDTO book = new PopularBookDTO();
            book.borrowingsCount = this.borrowingsCount;
            book.uniqueBookNumber = this.uniqueBookNumber;
            book.author = this.author;
            book.title = this.title;
            return book;
        }
    }
}
