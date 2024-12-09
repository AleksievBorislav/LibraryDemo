package com.library.repo.model;

public class Reader {
    long id;
    String name;
    short age;
    long EGN;
    int bookCount;

    public long getId() {
        return id;
    }

    public long getEGN() {
        return EGN;
    }

    public String getName() {
        return name;
    }

    public short getAge() {
        return age;
    }

    public int getBookCount() {
        return bookCount;
    }


    public static final class ReaderBuilder {
        private long id;
        private String name;
        private short age;
        private int bookCount;
        private long EGN;

        public ReaderBuilder() {
        }

        public static ReaderBuilder aReader() {
            return new ReaderBuilder();
        }

        public ReaderBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public ReaderBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ReaderBuilder withAge(short age) {
            this.age = age;
            return this;
        }

        public ReaderBuilder withBookCount(int bookCount) {
            this.bookCount = bookCount;
            return this;
        }

        public ReaderBuilder withEGN(long EGN) {
            this.EGN = EGN;
            return this;
        }

        public Reader build() {
            Reader reader = new Reader();
            reader.id = this.id;
            reader.age = this.age;
            reader.bookCount = this.bookCount;
            reader.name = this.name;
            reader.EGN = this.EGN;
            return reader;
        }
    }
}
