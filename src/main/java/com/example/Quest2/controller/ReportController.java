package com.example.Quest2.controller;

import com.example.Quest2.dto.ReportReceive;
import com.example.Quest2.dto.ReportResponse;
import com.example.Quest2.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public List<ReportResponse> findAll() {
        return reportService.findAll();
    }

    @GetMapping("/report/{id}")
    public ReportResponse findById(@PathVariable Integer id) {
        return reportService.findById(id);
    }

    @DeleteMapping("/report/{id}")
    public String deleteById(@PathVariable Integer id) {
        reportService.deleteById(id);
        return "Report delete from the database";
    }

    @PostMapping("/report")
    public String save(@RequestBody ReportReceive r) {
        reportService.save(r);
        return "Report saved successfully";
    }

    @PutMapping("/report/{id}")
    public String update(@RequestBody ReportReceive r, @PathVariable int id) {
        reportService.update(r, id);
        return "Report updated successfully";
    }
}
