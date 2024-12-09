package com.library.repo.model.mappers;

import com.library.repo.model.OverdueBorrowing;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OverdueBorrowingMapper implements RowMapper<OverdueBorrowing> {
    @Override
    public OverdueBorrowing mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OverdueBorrowing.OverdueBorrowingBuilder()
                .withEGN(rs.getLong("EGN"))
                .withBorrowedTime(rs.getObject("borrowed_time", LocalDateTime.class))
                .withUniqueBookNumber(rs.getLong("unique_book_number"))
                .build();
    }
}
