package com.library.config;

import com.library.servicetest.model.OverdueBorrowing;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CsvGenerator {
    private static final String filePath = "src/main/resources/reports";
    private static final String[] CSV_HEADER = {"EGN", "Unique book number", "Borrowed time"};

    public void generateCsv(List<OverdueBorrowing> overDues, String time) {

        File file = new File("%s/%s".formatted(filePath, time));
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeNext(CSV_HEADER);

            for (OverdueBorrowing borrowing : overDues) {
                writer.writeNext(new String[]{String.valueOf(borrowing.getEGN()),
                        String.valueOf(borrowing.getUniqueBookNumber()),
                        String.valueOf(borrowing.getBorrowedTime())});
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
