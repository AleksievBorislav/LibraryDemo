package com.library.controller;

import com.library.servicetest.model.OverdueBorrowing;
import com.library.service.BorrowingsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BorrowingsController {

    BorrowingsService service;

    public BorrowingsController(BorrowingsService service) {
        this.service = service;
    }

    @PostMapping("/borrowings/borrow")
    public void borrow(@RequestParam Long EGN,
                       Long uniqueBookNumber) {
        service.borrow(EGN, uniqueBookNumber);
    }

    @PutMapping("/borrowings/return")
    public void returnBook(@RequestParam Long EGN,
                           Long uniqueBookNumber) {
        service.returnBook(EGN, uniqueBookNumber);
    }

    @GetMapping("/borrowings/overdues")
    public List<OverdueBorrowing> getOverDues() {
        return service.getOverDues();
    }

}