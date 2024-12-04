package com.library.service;

import com.library.servicetest.ReportRepo;
import com.library.servicetest.model.OverdueBorrowing;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    ReportRepo repo;

    public ReportService(ReportRepo repo) {
        this.repo = repo;
    }

    public long createReport(int overDuesCount) {
        return repo.createReport(overDuesCount);
    }

    public void populateReport(List<OverdueBorrowing> list, long reportId) {
        repo.populateReport(list, reportId);
    }
}
