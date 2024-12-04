package com.library.servicetest;

import com.library.config.Logger;
import com.library.config.exceptions.BookAlreadyExistsException;
import com.library.config.exceptions.BookNotFoundException;
import com.library.config.exceptions.ReaderNotFoundException;
import com.library.servicetest.model.Book;
import com.library.servicetest.model.BookRequest;
import com.library.servicetest.model.PopularBookDTO;
import com.library.servicetest.model.SQL.BookSQL;
import com.library.servicetest.model.mappers.BookMapper;
import com.library.servicetest.model.mappers.PopularBookMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookRepo {

    NamedParameterJdbcTemplate template;

    public BookRepo(@Qualifier("dataSource") DataSource source) {
        template = new NamedParameterJdbcTemplate(source);
    }


    public List<Book> getBookByTitle(String title) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("title", title);
        try {
            return template.query(BookSQL.getBookByTitle, parameters, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException("Book not found with the title of '%s'.".formatted(title));
        }
    }

    public Book getBookByUniqueBookNumber(long UBN) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("UBN", UBN);
        try {
            return template.queryForObject(BookSQL.getBookByUBN, parameters, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException("Book not found with unique book number of %s.".formatted(UBN));
        }
    }

    public void addBook(BookRequest book) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("pageCount", book.getPageCount())
                .addValue("author", book.getAuthor())
                .addValue("UBN", book.getUniqueBookNumber())
                .addValue("price", book.getPrice());

        try {
            template.update(BookSQL.addBook, parameters);
        } catch (DuplicateKeyException e) {
            throw new BookAlreadyExistsException("The book already exists in the database.");
        }

    }

    public void incrementReaderCount(long UBN) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("UBN", UBN);
        template.update(BookSQL.incrementReaderCount, parameters);
    }

    public void deleteBook(long uniqueBookNumber) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("uniqueBookNumber", uniqueBookNumber);
        template.update(BookSQL.deleteBook, parameters);
    }

    public List<Book> getLastNBorrowedBooksByEGN(long EGN, int numberOfBooks) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("EGN", EGN)
                .addValue("numberOfBooks", numberOfBooks);
        try {
            return template.query(BookSQL.getLastNBorrowedBooksByEGN, parameters, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ReaderNotFoundException("No books found for the reader !");
        }
    }

    public List<Book> getNotReturnedBooks(long EGN) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("EGN", EGN);
        return template.query(BookSQL.getNotReturnedBooks, parameters, new BookMapper());
    }

    public List<PopularBookDTO> getMostRecentlyPopularBooks(){
        Logger.LOGGER.info("Redis provided no data, calling repo.");
        return template.query(BookSQL.getMostPopularBooks, new PopularBookMapper());
    }
}
