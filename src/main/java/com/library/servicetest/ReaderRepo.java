package com.library.servicetest;

import com.library.config.exceptions.ReaderAlreadyExistsException;
import com.library.config.exceptions.ReaderNotFoundException;
import com.library.servicetest.model.Reader;
import com.library.servicetest.model.ReaderRequest;
import com.library.servicetest.model.SQL.ReaderSQL;
import com.library.servicetest.model.mappers.ReaderMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ReaderRepo {


    NamedParameterJdbcTemplate template;

    public ReaderRepo(@Qualifier("dataSource") DataSource source) {
        template = new NamedParameterJdbcTemplate(source);
    }


    public Reader getReaderById(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("readerId", id);
        try {
            return template.queryForObject(ReaderSQL.getReaderById, parameters, new ReaderMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ReaderNotFoundException("Reader not found with ID of %s.".formatted(id));
        }
    }

    public Reader getReaderByEGN(long EGN) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("EGN", EGN);
        try {
            return template.queryForObject(ReaderSQL.getReaderByEGN, parameters, new ReaderMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ReaderNotFoundException("Reader not found with EGN of %s.".formatted(EGN));
        }
    }

    public void addReader(ReaderRequest reader) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("EGN", reader.getEgn())
                .addValue("name", reader.getName())
                .addValue("age", reader.getAge());

        try {
            template.update(ReaderSQL.addReader, parameters);
        } catch (DuplicateKeyException e) {
            throw new ReaderAlreadyExistsException("The reader already exists in the database.");
        }
    }

    public void incrementBookCount(long EGN) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("EGN", EGN);
        template.update(ReaderSQL.incrementBookCount, parameters);

    }

    public void deleteReader(long EGN) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("EGN", EGN);
        template.update(ReaderSQL.deleteReader, parameters);
    }

}
