package com.library.repo.model.SQL;

public class ReaderSQL {

    public static final String getReaderById = """
            SELECT reader_id, age, name, book_count, EGN
            FROM Reader
            WHERE reader_id = :readerId;
            """;

    public static final String getReaderByEGN = """
            SELECT reader_id, age, name, book_count, EGN
            FROM Reader
            WHERE egn = :EGN;
            """;

    public static final String addReader = """
            INSERT INTO Reader
            (name, age, egn)
            VALUES (:name, :age, :EGN);
            """;


    public static String incrementBookCount = """
            UPDATE Reader
            SET book_count = book_count + 1
            WHERE egn = :EGN;
            """;

    public static String deleteReader = """
            DELETE FROM Reader
            WHERE egn = :EGN;
            """;

}
