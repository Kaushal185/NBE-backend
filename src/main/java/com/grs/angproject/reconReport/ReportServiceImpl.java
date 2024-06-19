package com.grs.angproject.reconReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService  {
    @Autowired
    private ReportRepository reportRepository;
    @Override
    public List<ReportEntity> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<ReportEntity> getReportById(String Long) {
        return Optional.empty();
    }

    @Override
    public Optional<ReportEntity> getReportById(Long userId) {
        return reportRepository.findById(userId);
    }

    @Override
    public ReportEntity createReport(ReportEntity report) {
        return reportRepository.save(report);
    }
    @Override
    public ReportEntity updateReport(Long userId, ReportEntity reportDetails) {
        ReportEntity report = reportRepository.findById(userId).orElseThrow(() -> new RuntimeException("Report not found"));
        report.setSender(reportDetails.getSender());
        report.setReceiver(reportDetails.getReceiver());
        report.setAmount(reportDetails.getAmount());
        report.setCurrency(reportDetails.getCurrency());
        return reportRepository.save(report);
    }
    @Override
    public void deleteReport(Long userId) {
        reportRepository.deleteById(userId);
    }
}

