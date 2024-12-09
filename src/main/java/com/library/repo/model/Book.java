package com.library.repo.model;

import java.math.BigDecimal;

public class Book {
    long bookId;
    long uniqueBookNumber;
    String title;
    int pageCount;
    int readersCount;
    String author;
    BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public int getReadersCount() {
        return readersCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getTitle() {
        return title;
    }

    public long getBookId() {
        return bookId;
    }

    public String getAuthor() {
        return author;
    }

    public long getUniqueBookNumber() {
        return uniqueBookNumber;
    }

    public static final class BookBuilder {
        private long bookId;
        private long uniqueBookNumber;
        private String title;
        private int pageCount;
        private int readersCount;
        private String author;
        private BigDecimal price;

        public BookBuilder() {
        }

        public static BookBuilder aBook() {
            return new BookBuilder();
        }

        public BookBuilder withBookId(long bookId) {
            this.bookId = bookId;
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

        public BookBuilder withReadersCount(int readersCount) {
            this.readersCount = readersCount;
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

        public BookBuilder withUniqueBookNumber(long uniqueBookNumber) {
            this.uniqueBookNumber = uniqueBookNumber;
            return this;
        }

        public Book build() {
            Book book = new Book();
            book.bookId = this.bookId;
            book.uniqueBookNumber = this.uniqueBookNumber;
            book.readersCount = this.readersCount;
            book.pageCount = this.pageCount;
            book.price = this.price;
            book.author = this.author;
            book.title = this.title;
            return book;
        }
    }
}
