package com.library.config;

import com.library.config.exceptions.ReaderNotFoundException;
import com.library.servicetest.model.Borrowings;
import com.library.servicetest.model.OverdueBorrowing;
import com.library.servicetest.model.OverdueReport;
import com.library.servicetest.model.mappers.BorrowingsMapper;
import com.library.servicetest.model.mappers.OverdueBorrowingMapper;
import com.library.servicetest.model.mappers.ReportMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("test")
public class TestRepo {

    NamedParameterJdbcTemplate template;

    public TestRepo(@Qualifier("dataSource") DataSource source) {
        template = new NamedParameterJdbcTemplate(source);
    }

    public void borrowOverdue(long EGN, long UBN) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("EGN", EGN)
                .addValue("UBN", UBN);
        template.update(TestSQL.borrowOverDue, parameters);
    }

    public Borrowings getBorrowing(long EGN, long UBN) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("EGN", EGN)
                .addValue("UBN", UBN);
        try {
            return template.queryForObject(TestSQL.getBorrowing, parameters, new BorrowingsMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ReaderNotFoundException("The reader has not yet borrowed book with the unique number of %s.".formatted(UBN));
        }
    }

    public List<OverdueReport> getReports(long EGN) {
            ReportMapper reportMapper = new ReportMapper();
            OverdueBorrowingMapper borrowingMapper = new OverdueBorrowingMapper();

            return template.query(TestSQL.getReports, new MapSqlParameterSource("egn", EGN), rs -> {
                Map<Long, OverdueReport> reportMap = new HashMap<>();

                while (rs.next()) {
                    long reportId = rs.getLong("report_id");

                    OverdueReport report = reportMap.computeIfAbsent(reportId, id -> {
                        try {
                            return reportMapper.mapRow(rs, rs.getRow());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    if (rs.getObject("overdue_borrowing_id") != null) {
                        OverdueBorrowing borrowing = borrowingMapper.mapRow(rs, rs.getRow());
                        report.getOverdueBorrowingList().add(borrowing);
                    }
                }

                return new ArrayList<>(reportMap.values());
            });
    }


    public void deleteAllBorrowings() {
        SqlParameterSource parameters = new MapSqlParameterSource();
        template.update(TestSQL.deleteAllBorrowings, parameters);
    }

}
