package com.library.service;

import com.library.servicetest.ReaderRepo;
import com.library.servicetest.model.Reader;
import com.library.servicetest.model.ReaderRequest;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {

    ReaderRepo repo;

    public ReaderService(ReaderRepo repo) {
        this.repo = repo;
    }

    public Reader getReaderById(long id) {
        return repo.getReaderById(id);
    }

    public Reader getReaderByEGN(long EGN) {
        return repo.getReaderByEGN(EGN);
    }

    public void addReader(ReaderRequest reader) {
        repo.addReader(reader);
    }

    public void deleteReader(long EGN) {
        repo.deleteReader(EGN);
    }

    public void incrementBookCount(long EGN) {
        repo.incrementBookCount(EGN);
    }

}
