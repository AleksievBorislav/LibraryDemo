package com.library.repo.model;

public class ReaderRequest {
    long egn;
    String name;
    short age;

    public long getEgn() {
        return egn;
    }

    public String getName() {
        return name;
    }

    public short getAge() {
        return age;
    }

    public static final class ReaderBuilder {
        private long egn;
        private String name;
        private short age;

        public ReaderBuilder() {
        }

        public static ReaderBuilder aReader() {
            return new ReaderBuilder();
        }

        public ReaderBuilder withEGN(long EGN) {
            this.egn = EGN;
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

        public ReaderRequest build() {
            ReaderRequest reader = new ReaderRequest();
            reader.egn = this.egn;
            reader.age = this.age;
            reader.name = this.name;
            return reader;
        }
    }
}
