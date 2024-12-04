package com.library.servicetest.model.SQL;

public class BookSQL {
    public static String getBookByTitle = """
            SELECT book_id, unique_book_number, title, page_count, readers_count, author, price 
            FROM Book
            WHERE title = :title;
            """;

    public static String getBookByUBN = """
            SELECT book_id, unique_book_number, title, page_count, readers_count, author, price
            FROM Book
            WHERE unique_book_number = :UBN;
            """;

    public static String addBook = """
            INSERT INTO Book
            (title, page_count, unique_book_number, author, price)
            VALUES(:title, :pageCount, :UBN, :author, :price);
            """;

    public static String incrementReaderCount = """
            UPDATE Book
            SET readers_count = readers_count + 1
            WHERE unique_book_number = :UBN;
            """;

    public static String deleteBook = """
            DELETE FROM Book
            WHERE unique_book_number = :uniqueBookNumber
            """;

    public static String getLastNBorrowedBooksByEGN = """
            SELECT book.book_id, book.title, book.author, book.unique_book_number, book.page_count, book.price, book.readers_count
            FROM Book
            INNER JOIN borrowings ON book.unique_book_number = borrowings.unique_book_number
            INNER JOIN reader ON borrowings.EGN = reader.EGN
            WHERE reader.EGN = :EGN
            ORDER BY borrowings.borrowed_time DESC
            LIMIT :numberOfBooks
            """;

    public static String getNotReturnedBooks = """
            SELECT book.book_id, book.title, book.author, book.unique_book_number, book.page_count, book.price, book.readers_count
            FROM Book
            INNER JOIN borrowings ON book.unique_book_number = borrowings.unique_book_number
            INNER JOIN reader ON borrowings.EGN = reader.EGN
            WHERE reader.egn = :EGN
            AND borrowings.is_returned = FALSE;
            """;

    public static String getMostPopularBooks = """
            WITH BorrowingCounts AS (
                SELECT
                    bo.unique_book_number,
                    COUNT((bo.unique_book_number, bo.EGN)) AS borrowings_count
                FROM borrowings bo
                WHERE bo.borrowed_time >= CURRENT_DATE - INTERVAL '7 days'
                GROUP BY bo.unique_book_number
            )
            SELECT
                b.unique_book_number,
                b.title,
                b.author,
                bc.borrowings_count
            FROM BorrowingCounts bc
            JOIN Book b ON b.unique_book_number = bc.unique_book_number
            ORDER BY bc.borrowings_count DESC
            LIMIT 10;
            """;
}
