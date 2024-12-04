package com.library.servicetest.model.SQL;

public class BorrowingsSQL {

    public static String borrow = """
            INSERT INTO Borrowings (egn, unique_book_number, borrowed_time)
            VALUES (:EGN, :UBN, NOW());
            """;

    public static String returnBook = """
            UPDATE Borrowings
            SET is_returned = true, returned_time = NOW()
            WHERE unique_book_number = :UBN
            AND egn = :EGN
            AND is_returned = false;
            """;

    public static String getOverDues = """
            SELECT egn, unique_book_number, borrowed_time
            FROM Borrowings
            WHERE borrowed_time <= 'now'::timestamp - '1 month'::interval
            AND is_returned = false;
            """;
}
