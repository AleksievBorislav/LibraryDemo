package com.library.servicetest.model.mappers;

import com.library.servicetest.model.Borrowings;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BorrowingsMapper implements RowMapper<Borrowings> {
    @Override
    public Borrowings mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Borrowings.BorrowingsBuilder()
                .withEGN(rs.getLong("EGN"))
                .withUniqueBookNumber(rs.getLong("unique_book_number"))
                .withBorrowTime(rs.getObject("borrowed_time", LocalDateTime.class))
                .withReturnedTime(rs.getObject("returned_time", LocalDateTime.class))
                .withIsReturned(rs.getBoolean("is_returned"))
                .build();
    }
}
