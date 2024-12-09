package com.library.config;

import com.library.repo.BorrowingsRepo;
import com.library.repo.model.Borrowings;
import com.library.repo.model.OverdueReport;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("test")
public class TestService {

    BorrowingsRepo borrowingsRepo;
    TestRepo testRepo;

    public TestService(TestRepo testRepo, BorrowingsRepo borrowingsRepo) {
        this.testRepo = testRepo;
        this.borrowingsRepo = borrowingsRepo;
    }

    public void borrowOverdue(long EGN, long UBN) {
        testRepo.borrowOverdue(EGN, UBN);
    }

    public void deleteAllBorrowings() {
        testRepo.deleteAllBorrowings();
    }

    public Borrowings getBorrowing(long EGN, long uniqueBookNumber) {
        return testRepo.getBorrowing(EGN, uniqueBookNumber);
    }

    public List<OverdueReport> getReports(long EGN) {
        return testRepo.getReports(EGN);
    }

}
