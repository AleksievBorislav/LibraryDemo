package com.library.service;

import com.library.config.Logger;
import com.library.repo.BorrowingsRepo;
import com.library.repo.model.OverdueBorrowing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BorrowingsService {

    BorrowingsRepo repo;
    BookService bookService;
    ReaderService readerService;

    public BorrowingsService(BorrowingsRepo repo,
                             BookService bookService,
                             ReaderService readerService) {
        this.repo = repo;
        this.bookService = bookService;
        this.readerService = readerService;
    }

    @Transactional
    public void borrow(long EGN, long uniqueBookNumber) {
        try {
            repo.borrow(EGN, uniqueBookNumber);
            bookService.incrementReaderCount(uniqueBookNumber);
            readerService.incrementBookCount(EGN);
        } catch (Exception e) {
            Logger.LOGGER.error("Borrowing a book transaction failed for book %s"
                    .formatted(uniqueBookNumber));
            throw e;
        }
    }

    public void returnBook(long EGN, long uniqueBookNumber) {
        repo.returnBook(EGN, uniqueBookNumber);
    }

    public List<OverdueBorrowing> getOverDues() {
        return repo.getOverDues();
    }

}
