package com.library.controller;

import com.library.service.jobs.DailyCountReportJob;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    DailyCountReportJob job;

    public ReportController(DailyCountReportJob job) {
        this.job = job;
    }

    @PostMapping("/report")
    public void generateReport() {
        job.execute();
    }

}
