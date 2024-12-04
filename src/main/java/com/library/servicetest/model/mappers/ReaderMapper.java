package com.library.servicetest.model.mappers;

import com.library.servicetest.model.Reader;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderMapper implements RowMapper<Reader> {
    @Override
    public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Reader.ReaderBuilder()
                .withId(rs.getLong("reader_id"))
                .withAge(rs.getShort("age"))
                .withName(rs.getString("name"))
                .withBookCount(rs.getInt("book_count"))
                .withEGN(rs.getLong("EGN"))
                .build();
    }
}
