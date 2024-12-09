package com.library.repo.model.mappers;

import com.library.repo.model.OverdueReport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportMapper implements RowMapper<OverdueReport> {
    @Override
    public OverdueReport mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OverdueReport.OverdueReportBuilder()
                .withId(rs.getInt("report_id"))
                .withOverdueBorrowings(rs.getShort("overdues_count"))
                .withReportDate(rs.getTimestamp("report_time").toLocalDateTime())
                .withOverdueBorrowingList(new ArrayList<>())
                .build();
    }
}
