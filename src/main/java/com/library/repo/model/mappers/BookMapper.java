package com.library.repo.model.mappers;

import com.library.repo.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book.BookBuilder()
                .withBookId(rs.getLong("book_id"))
                .withUniqueBookNumber(rs.getLong("unique_book_number"))
                .withPageCount(rs.getInt("page_count"))
                .withPrice(rs.getBigDecimal("price"))
                .withReadersCount(rs.getInt("readers_count"))
                .withAuthor(rs.getString("author"))
                .withTitle(rs.getString("title"))
                .build();
    }
}
