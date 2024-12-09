package com.library.repo;

import com.library.config.Logger;
import com.library.repo.model.OverdueBorrowing;
import com.library.repo.model.SQL.ReportSQL;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ReportRepo {

    NamedParameterJdbcTemplate template;

    public ReportRepo(@Qualifier("dataSource") DataSource source) {
        template = new NamedParameterJdbcTemplate(source);
    }


    public void populateReport(List<OverdueBorrowing> list, long reportId) {
        List<Map<String, Object>> batchParams = new ArrayList<>();
        for (OverdueBorrowing borrowing : list) {
            batchParams.add(Map.of(
                    "reportId", reportId,
                    "UBN", borrowing.getUniqueBookNumber(),
                    "EGN", borrowing.getEGN(),
                    "borrowedTime", borrowing.getBorrowedTime()
            ));
        }

        template.batchUpdate(ReportSQL.populateReport, batchParams.toArray(new Map[0]));

    }

    public long createReport(long overduesCount) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("overduesCount", overduesCount);

        try {
            return template.queryForObject(ReportSQL.createReport, parameters, Long.class);
        } catch (Exception e) {
            Logger.LOGGER.info("Error generating report!!!");
            throw e;
        }
    }

}
