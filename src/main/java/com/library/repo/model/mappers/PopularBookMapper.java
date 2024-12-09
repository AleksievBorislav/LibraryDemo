package com.library.repo.model.mappers;

import com.library.repo.model.PopularBookDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PopularBookMapper implements RowMapper<PopularBookDTO> {
    @Override
    public PopularBookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PopularBookDTO.BookBuilder()
                .withUniqueBookNumber(rs.getLong("unique_book_number"))
                .withAuthor(rs.getString("author"))
                .withTitle(rs.getString("title"))
                .withBookId(rs.getInt("borrowings_count"))
                .build();
    }
}
