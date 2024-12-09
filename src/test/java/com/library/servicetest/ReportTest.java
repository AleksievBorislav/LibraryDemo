package com.library.servicetest;

import com.library.BaseTest;
import com.library.config.TestService;
import com.library.repo.model.BookRequest;
import com.library.repo.model.OverdueBorrowing;
import com.library.repo.model.OverdueReport;
import com.library.repo.model.ReaderRequest;
import com.library.service.BookService;
import com.library.service.BorrowingsService;
import com.library.service.ReaderService;
import com.library.service.ReportService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class ReportTest extends BaseTest{

    @Autowired
    ReportService reportService;

    @Autowired
    TestService testService;

    @Autowired
    BorrowingsService borrowingsService;

    @Autowired
    ReaderService readerService;

    @Autowired
    BookService bookService;

    @Test
    @Order(1)
    public void createReport() {

        bookService.addBook(new BookRequest.BookBuilder()
                .withuniqueBookNumber(111111113)
                .withAuthor("S.M.")
                .withPageCount(124)
                .withPrice(BigDecimal.valueOf(23.22))
                .withTitle("TestTitle1")
                .build());

        bookService.addBook(new BookRequest.BookBuilder()
                .withuniqueBookNumber(222222223L)
                .withAuthor("S.M.")
                .withPageCount(124)
                .withPrice(BigDecimal.valueOf(23.22))
                .withTitle("TestTitle2")
                .build());

        bookService.addBook(new BookRequest.BookBuilder()
                .withuniqueBookNumber(333333334L)
                .withAuthor("S.M.")
                .withPageCount(124)
                .withPrice(BigDecimal.valueOf(23.22))
                .withTitle("TestTitle3")
                .build());

        readerService.addReader(new ReaderRequest.ReaderBuilder()
                .withEGN(2222222228L)
                .withAge((short) 92)
                .withName("Peter Jackson")
                .build());

        testService.borrowOverdue(2222222228L, 111111113);
        testService.borrowOverdue(2222222228L, 222222223L);
        testService.borrowOverdue(2222222228L, 333333334L);

        List<OverdueBorrowing> overDues = borrowingsService.getOverDues();
        assertEquals(overDues.size(), 3);
        assertEquals(overDues.getFirst().getEGN(),2222222228L);
        assertTrue(overDues.stream().anyMatch(o -> o.getUniqueBookNumber() == 333333334L));

        long reportId = reportService.createReport(overDues.size());
        reportService.populateReport(overDues, reportId);
        List<OverdueReport> reports =  testService.getReports(2222222228L);
        assertEquals(reports.getFirst().getOverdueBorrowings(), 3);
        assertEquals(reports.getFirst().getOverdueBorrowingList().size(), 3);
        assertEquals(reports.getFirst().getOverdueBorrowingList().getFirst().getUniqueBookNumber(), 111111113);
        assertEquals(reports.getFirst().getOverdueBorrowingList().getLast().getUniqueBookNumber(), 333333334L);
        testService.deleteAllBorrowings();
    }
}
