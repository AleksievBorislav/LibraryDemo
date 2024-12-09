package com.library.repo;


import com.library.config.Logger;
import com.library.config.exceptions.FailedToBorrowException;
import com.library.repo.model.OverdueBorrowing;
import com.library.repo.model.SQL.BorrowingsSQL;
import com.library.repo.model.mappers.OverdueBorrowingMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class BorrowingsRepo {

    NamedParameterJdbcTemplate template;

    public BorrowingsRepo(@Qualifier("dataSource") DataSource source) {
        template = new NamedParameterJdbcTemplate(source);
    }

    public void borrow(long EGN, long uniqueBookNumber) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("EGN", EGN)
                .addValue("UBN", uniqueBookNumber);

        try {
            template.update(BorrowingsSQL.borrow, parameters);
        } catch (RuntimeException e) {
            throw new FailedToBorrowException("Failed to borrow %s".formatted(uniqueBookNumber));
        }
    }

    public void returnBook(long EGN, long uniqueBookNumber) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("EGN", EGN)
                .addValue("UBN", uniqueBookNumber);
        template.update(BorrowingsSQL.returnBook, parameters);
    }

    public List<OverdueBorrowing> getOverDues() {

        List<OverdueBorrowing> borrowingList = template
                .query(BorrowingsSQL.getOverDues, new OverdueBorrowingMapper());
        if (borrowingList.isEmpty()) {
            Logger.LOGGER.info("Congrats, no over dues! ");
        }

        return borrowingList;
    }
}
