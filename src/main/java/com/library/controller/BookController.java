package com.library.controller;

import com.library.repo.model.Book;
import com.library.repo.model.BookRequest;
import com.library.repo.model.PopularBookDTO;
import com.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books/title")
    public List<Book> getBookByTitle(@RequestParam String title) {
        return service.getBookByTitle(title);
    }

    @GetMapping("/books/UBN")
    public Book getBookByUBN(@RequestParam long uniqueBookNumber) {
        return service.getBookByUniqueBookNumber(uniqueBookNumber);
    }

    @PostMapping("/books")
    public void addBook(@RequestBody BookRequest book) {
        service.addBook(book);
    }

    @GetMapping("/books/borrowed")
    public List<Book> getMostPopularBooks(@RequestParam long EGN,
                                          @RequestParam int numberOfBooks) {
        return service.getLastNBorrowedBooksByEGN(EGN, numberOfBooks);
    }

    @GetMapping("/books/popular")
    public List<PopularBookDTO> getMostPopularBooks() {
        return service.getMostRecentlyPopularBooks();
    }

    @GetMapping("/books/not-returned")
    public List<Book> getNotReturnedBooks(@RequestParam long EGN) {
        return service.getNotReturnedBooks(EGN);
    }

    @DeleteMapping("/book/delete")
    public void deleteBook(@RequestParam long uniqueBookNumber) {
        service.deleteBook(uniqueBookNumber);
    }
}
