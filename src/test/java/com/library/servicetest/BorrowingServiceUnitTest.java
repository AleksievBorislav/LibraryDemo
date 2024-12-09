package com.library.servicetest;

import com.library.BaseTest;
import com.library.config.TestService;
import com.library.repo.model.*;
import com.library.service.BookService;
import com.library.service.BorrowingsService;
import com.library.service.ReaderService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class BorrowingServiceUnitTest extends BaseTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    BorrowingsService borrowingsService;

    @Autowired
    ReaderService readerService;

    @Autowired
    BookService bookService;

    @Autowired
    TestService testService;

    @Test
    @Order(1)
    public void borrow() {

        bookService.addBook(new BookRequest.BookBuilder()
                .withuniqueBookNumber(121111111)
                .withAuthor("S.M.")
                .withPageCount(124)
                .withPrice(BigDecimal.valueOf(23.22))
                .withTitle("TestTitle1")
                .build());

        bookService.addBook(new BookRequest.BookBuilder()
                .withuniqueBookNumber(232222222L)
                .withAuthor("S.M.")
                .withPageCount(124)
                .withPrice(BigDecimal.valueOf(23.22))
                .withTitle("TestTitle2")
                .build());

        bookService.addBook(new BookRequest.BookBuilder()
                .withuniqueBookNumber(343333333L)
                .withAuthor("S.M.")
                .withPageCount(124)
                .withPrice(BigDecimal.valueOf(23.22))
                .withTitle("TestTitle3")
                .build());

        readerService.addReader(new ReaderRequest.ReaderBuilder()
                .withEGN(2322222222L)
                .withAge((short) 92)
                .withName("Peter Jackson")
                .build());


        int bookCount = readerService.getReaderByEGN(2322222222L).getBookCount();
        int readCount = bookService.getBookByUniqueBookNumber(121111111).getReadersCount();
        assertTrue(bookService.getLastNBorrowedBooksByEGN(2322222222L, 100).isEmpty());

        borrowingsService.borrow(2322222222L, 121111111);

        assertFalse(bookService.getLastNBorrowedBooksByEGN(2322222222L, 100).isEmpty());
        assertEquals(bookCount + 1, readerService.getReaderByEGN(2322222222L).getBookCount());
        assertEquals(readCount + 1, bookService.getBookByUniqueBookNumber(121111111).getReadersCount());

    }

    @Test
    @Order(2)
    public void returnBook() {

        Borrowings testBorrowing = testService.getBorrowing(2322222222L, 121111111);

        assertFalse(testBorrowing.isReturned());
        borrowingsService.returnBook(2322222222L, 121111111);
        assertTrue(bookService.getNotReturnedBooks(2322222222L).isEmpty());

        testBorrowing = testService.getBorrowing(2322222222L, 121111111);

        assertNotEquals(testBorrowing.getBorrowTime(), testBorrowing.getReturnedTime());
        assertTrue(testBorrowing.isReturned());

    }


    @Test
    @Order(3)
    public void getNumberOfLastBorrowedBooks() {

        borrowingsService.borrow(2322222222L, 232222222L);
        borrowingsService.borrow(2322222222L, 343333333L);
        List<Book> testList = bookService.getLastNBorrowedBooksByEGN(2322222222L, 2);

        assertEquals("TestTitle3", testList.getFirst().getTitle());
        assertEquals("TestTitle2", testList.get(1).getTitle());
    }

    @Test
    @Order(4)
    public void getOverDues() {
        List<OverdueBorrowing> testList = borrowingsService.getOverDues();
        assertTrue(testList.isEmpty());
        testService.borrowOverdue(2322222222L, 121111111);
        testList = borrowingsService.getOverDues();
        assertEquals(2322222222L, testList.getFirst().getEGN());
        assertEquals(121111111, testList.getFirst().getUniqueBookNumber());


    }

    @Test
    @Order(5)
    public void getPopularBooks() throws InterruptedException {

        borrowingsService.borrow(2322222222L, 232222222);
        borrowingsService.borrow(2322222222L, 343333333);
        borrowingsService.borrow(2322222222L, 343333333);

        List<PopularBookDTO> list = bookService.getMostRecentlyPopularBooks();
        assertEquals(list.getFirst().getBorrowingsCount(), 3);
        assertEquals(list.getFirst().getUniqueBookNumber(), 343333333);
        assertEquals(list.get(1).getBorrowingsCount(), 2);
        assertEquals(list.get(1).getUniqueBookNumber(), 232222222);
        assertEquals(list.size(), 3);

        List<PopularBookDTO> cachedBooks = (List<PopularBookDTO>) redisTemplate.opsForValue().get("books::mostRecentlyPopular");
        assertInstanceOf(List.class, cachedBooks);

        assertEquals(list.size(), cachedBooks.size());

        for (int i = 0; i < list.size(); i++) {
            PopularBookDTO listItem = list.get(i);
            PopularBookDTO cachedItem = cachedBooks.get(i);

            assertEquals(listItem.getBorrowingsCount(), cachedItem.getBorrowingsCount());
            assertEquals(listItem.getUniqueBookNumber(), cachedItem.getUniqueBookNumber());
            assertEquals(listItem.getTitle(), cachedItem.getTitle());
            assertEquals(listItem.getAuthor(), cachedItem.getAuthor());
        }

        testService.deleteAllBorrowings();
    }

}