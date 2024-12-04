package com.library.config;

public class TestSQL {

    public static String borrowOverDue = """
            INSERT INTO Borrowings (egn, unique_book_number, borrowed_time)
            VALUES (:EGN, :UBN, 'now'::timestamp - '1 month'::interval);
            """;

    public static String getBorrowing = """
            SELECT egn, unique_book_number, borrowed_time, is_returned, returned_time
            FROM Borrowings
            WHERE egn = :EGN
            AND unique_book_number = :UBN
            ORDER BY borrowed_time DESC
            LIMIT 1;
            """;

    public static String getReports = """
            SELECT r.report_id, r.overdues_count, r.report_time,
            b.overdue_borrowing_id, b.egn, b.unique_book_number, b.borrowed_time
            FROM report r
            LEFT JOIN Overdue_borrowing b ON r.report_id = b.report_id
            WHERE b.egn = :egn;
            """;

    public static String deleteAllBorrowings = """
            DELETE FROM Borrowings
            """;

}
