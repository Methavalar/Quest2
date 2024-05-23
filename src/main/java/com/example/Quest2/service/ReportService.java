package com.example.Quest2.service;

import com.example.Quest2.dto.ReportReceive;
import com.example.Quest2.dto.ReportResponse;

import java.util.List;

public interface ReportService {

    List<ReportResponse> findAll();

    ReportResponse findById(Integer id);

    void deleteById(Integer id);

    void save(ReportReceive r);

    void update(ReportReceive r, Integer id);
}
