package com.library.servicetest.model;

import java.math.BigDecimal;

public class BookRequest {
    long uniqueBookNumber;
    String title;
    int pageCount;
    BigDecimal price;
    String author;

    public BigDecimal getPrice() {
        return price;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getTitle() {
        return title;
    }

    public long getUniqueBookNumber() {
        return uniqueBookNumber;
    }

    public String getAuthor() {
        return author;
    }

    public static final class BookBuilder {
        private long uniqueBookNumber;
        private String title;
        private int pageCount;
        private BigDecimal price;
        private String author;

        public BookBuilder() {
        }

        public static BookBuilder aBook() {
            return new BookBuilder();
        }

        public BookBuilder withuniqueBookNumber(long uniqueBookNumber) {
            this.uniqueBookNumber = uniqueBookNumber;
            return this;
        }

        public BookBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder withPageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public BookBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BookRequest build() {
            BookRequest book = new BookRequest();
            book.uniqueBookNumber = this.uniqueBookNumber;
            book.pageCount = this.pageCount;
            book.price = this.price;
            book.author = this.author;
            book.title = this.title;
            return book;
        }
    }
}
