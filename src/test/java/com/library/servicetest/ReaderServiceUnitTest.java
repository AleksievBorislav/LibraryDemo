package com.library.servicetest;

import com.library.BaseTest;
import com.library.config.exceptions.ReaderAlreadyExistsException;
import com.library.config.exceptions.ReaderNotFoundException;
import com.library.repo.model.Reader;
import com.library.repo.model.ReaderRequest;
import com.library.service.ReaderService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class ReaderServiceUnitTest extends BaseTest{

    @Autowired
    private ReaderService readerService;

    @Test
    @Order(1)
    public void addReader() {

        readerService.addReader(new ReaderRequest.ReaderBuilder()
                .withEGN(9999999988L)
                .withAge((short) 92)
                .withName("Peter Jackson")
                .build());

        assertThrows(ReaderAlreadyExistsException.class, () ->
        {
            readerService.addReader(new ReaderRequest.ReaderBuilder()
                    .withEGN(9999999988L)
                    .withAge((short) 92)
                    .withName("Peter Jackson")
                    .build());
        });
    }


    @Test
    @Order(2)
    public void getReader() {
        Reader reader = readerService.getReaderByEGN(9999999988L);

        assertEquals("Peter Jackson", reader.getName());
        assertEquals(92, reader.getAge());
        assertEquals(0, reader.getBookCount());
        assertEquals(9999999988L, reader.getEGN());

        reader = readerService.getReaderById(reader.getId());

        assertEquals("Peter Jackson", reader.getName());
        assertEquals(92, reader.getAge());
        assertEquals(0, reader.getBookCount());
        assertEquals(9999999988L, reader.getEGN());
    }

    @Test
    @Order(3)
    public void deleteReader() {
        readerService.deleteReader(9999999988L);
        assertThrows(ReaderNotFoundException.class, () ->
        {
            readerService.getReaderByEGN(9999999988L);
        });
    }
}
