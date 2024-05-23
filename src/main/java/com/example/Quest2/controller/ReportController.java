package com.example.Quest2.controller;

import com.example.Quest2.dto.ReportReceive;
import com.example.Quest2.dto.ReportResponse;
import com.example.Quest2.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<List<ReportResponse>> findAll() {
        List<ReportResponse> reports = reportService.findAll();
        if (reports == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<ReportResponse> findById(@PathVariable Integer id) {
        ReportResponse report = reportService.findById(id);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    @DeleteMapping("/report/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        reportService.deleteById(id);
        return ResponseEntity.ok("Report delete from the database");
    }

    @PostMapping("/report")
    public ResponseEntity<String> save(@RequestBody ReportReceive r) {
        reportService.save(r);
        return ResponseEntity.ok("Report saved from the database");
    }

    @PutMapping("/report/{id}")
    public ResponseEntity<String> update(@RequestBody ReportReceive r, @PathVariable int id) {
        reportService.update(r, id);
        return ResponseEntity.ok("Report updated from the database");
    }
}
