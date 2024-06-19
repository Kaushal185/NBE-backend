package com.grs.angproject.reconReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {
    @Autowired
    private ReportService reportService;


//    @GetMapping("/all")
//    public List<ReportEntity> getAllReports() {
//        return reportService.getAllReports();
//    }

    @GetMapping("/all")
    public List<ReportEntity> getNotnull() {
        return reportService.getAllReports()
                .stream()
                .filter(report -> report.getSender() != null &&
                        report.getReceiver() != null &&
                        report.getAmount() != null &&
                        report.getCurrency() != null &&
                        report.getReferenceNumber() != null &&
                        report.getStatus() != null)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ReportEntity> getReportById(@PathVariable String userId) {
        Optional<ReportEntity> report = reportService.getReportById(userId);
        if (report.isPresent()) {
            return ResponseEntity.ok(report.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ReportEntity createReport(@RequestBody ReportEntity report) {
        return reportService.createReport(report);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ReportEntity> updateReport(@PathVariable Long userId, @RequestBody ReportEntity reportDetails) {
        ReportEntity updatedReport = reportService.updateReport(userId, reportDetails);
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long userId) {
        reportService.deleteReport(userId);
        return ResponseEntity.noContent().build();
    }
}
