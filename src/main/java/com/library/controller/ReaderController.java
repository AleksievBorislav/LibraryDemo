package com.library.controller;

import com.library.servicetest.model.Reader;
import com.library.servicetest.model.ReaderRequest;
import com.library.service.ReaderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReaderController {

    ReaderService service;

    public ReaderController(ReaderService service) {
        this.service = service;
    }

    @GetMapping("/readers/{id}/")
    public Reader getReaderById(@PathVariable long id) {
        return service.getReaderById(id);
    }

    @GetMapping("/readers")
    public Reader getReaderByEGN(@RequestParam long EGN) {
        return service.getReaderByEGN(EGN);
    }

    @PostMapping("/readers")
    public void addReader(@RequestBody ReaderRequest reader) {
        service.addReader(reader);
    }

    @DeleteMapping("/reader/delete")
    public void deleteReader(@RequestParam long EGN) {
        service.deleteReader(EGN);
    }
}
