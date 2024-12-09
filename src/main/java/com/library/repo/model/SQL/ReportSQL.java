package com.library.repo.model.SQL;

public class ReportSQL {

    public static String createReport = """
            INSERT INTO Report(
            overdues_count)
            VALUES (:overduesCount)
            RETURNING report_id;
            """;

    public static String populateReport = """
            INSERT INTO Overdue_borrowing (report_id, unique_book_number, egn, borrowed_time)
            VALUES (:reportId, :UBN, :EGN, :borrowedTime)
            """;

}
