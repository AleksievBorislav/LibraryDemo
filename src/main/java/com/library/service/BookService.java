package com.library.service;

import com.library.servicetest.BookRepo;
import com.library.servicetest.model.Book;
import com.library.servicetest.model.BookRequest;
import com.library.servicetest.model.PopularBookDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepo repo;

    public BookService(BookRepo repo) {
        this.repo = repo;
    }

    public List<Book> getBookByTitle(String title) {
        return repo.getBookByTitle(title);
    }

    public Book getBookByUniqueBookNumber(long UBN) {
        return repo.getBookByUniqueBookNumber(UBN);
    }

    public void addBook(BookRequest book) {
        repo.addBook(book);
    }

    public void incrementReaderCount(long UBN) {
        repo.incrementReaderCount(UBN);
    }

    public void deleteBook(long uniqueBookNumber) {
        repo.deleteBook(uniqueBookNumber);
    }

    public List<Book> getLastNBorrowedBooksByEGN(long EGN, int numberOfBooks) {
        return repo.getLastNBorrowedBooksByEGN(EGN, numberOfBooks);
    }

    public List<Book> getNotReturnedBooks(long EGN) {
        return repo.getNotReturnedBooks(EGN);
    }

    @Cacheable(value = "books", key = "'mostRecentlyPopular'")
    public List<PopularBookDTO> getMostRecentlyPopularBooks(){
        return repo.getMostRecentlyPopularBooks();
    }
}
