package com.library.service.jobs;

import com.library.config.CsvGenerator;
import com.library.config.Logger;
import com.library.repo.model.OverdueBorrowing;
import com.library.service.BorrowingsService;
import com.library.service.ReportService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
public class DailyCountReportJob {
    CsvGenerator csvGenerator;
    BorrowingsService borrowingsService;
    ReportService reportService;

    public DailyCountReportJob(CsvGenerator csvGenerator, BorrowingsService borrowingsService, ReportService reportService) {
        this.reportService = reportService;
        this.csvGenerator = csvGenerator;
        this.borrowingsService = borrowingsService;
    }

    @Scheduled(cron = "00 30 23 * * *")
    @Transactional
    public void execute() {
        List<OverdueBorrowing> list = borrowingsService.getOverDues();
        reportService.populateReport(list, reportService.createReport(list.size()));
        csvGenerator.generateCsv(list, LocalDate.now().toString());
        Logger.LOGGER.info("Daily report generated!");
    }
}
