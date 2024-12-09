package com.library.servicetest;

import com.library.BaseTest;
import com.library.config.exceptions.BookAlreadyExistsException;
import com.library.config.exceptions.BookNotFoundException;
import com.library.repo.model.Book;
import com.library.repo.model.BookRequest;
import com.library.service.BookService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class BookServiceUnitTest extends BaseTest{


    @Autowired
    private BookService bookService;

    @Test
    @Order(1)
    public void addBook() {
        bookService.addBook(new BookRequest.BookBuilder()
                .withuniqueBookNumber(125126135)
                .withAuthor("S.M.")
                .withPageCount(124)
                .withPrice(BigDecimal.valueOf(23.22))
                .withTitle("BookTitle")
                .build());

        assertThrows(BookAlreadyExistsException.class, () ->
        {
            bookService.addBook(new BookRequest.BookBuilder()
                    .withuniqueBookNumber(125126135)
                    .withAuthor("S.M.")
                    .withPageCount(124)
                    .withPrice(BigDecimal.valueOf(23.22))
                    .withTitle("BookTitle")
                    .build());
        });

    }

    @Test
    @Order(2)
    public void getBook() {
        Book book = bookService.getBookByUniqueBookNumber(125126135);

        assertEquals("BookTitle", book.getTitle());
        assertEquals("S.M.", book.getAuthor());
        assertEquals(BigDecimal.valueOf(23.22), book.getPrice());
        assertEquals(125126135, book.getUniqueBookNumber());
        assertEquals(124, book.getPageCount());

        book = bookService.getBookByTitle("BookTitle").getFirst();

        assertEquals("BookTitle", book.getTitle());
        assertEquals("S.M.", book.getAuthor());
        assertEquals(BigDecimal.valueOf(23.22), book.getPrice());
        assertEquals(125126135, book.getUniqueBookNumber());
        assertEquals(124, book.getPageCount());

    }

    @Test
    @Order(3)
    public void deleteBook() {
        bookService.deleteBook(125126135);
        assertThrows(BookNotFoundException.class, () ->
        {
            bookService.getBookByUniqueBookNumber(125126135);
        });
    }

}
