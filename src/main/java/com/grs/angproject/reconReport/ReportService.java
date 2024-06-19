package com.grs.angproject.reconReport;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    public List<ReportEntity> getAllReports();
    public Optional<ReportEntity> getReportById(String Long);
    public ReportEntity updateReport(Long userId, ReportEntity reportDetails);

    public void deleteReport(Long userId);

    Optional<ReportEntity> getReportById(Long userId);

    ReportEntity createReport(ReportEntity report);
}
