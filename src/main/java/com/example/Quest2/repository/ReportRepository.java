package com.example.Quest2.repository;

import com.example.Quest2.dto.ReportReceive;
import com.example.Quest2.dto.ReportResponse;
import com.example.Quest2.model.Report;
import com.example.Quest2.model.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportRepository {

    public List<ReportResponse> findAll(Session session) {
        String query = "SELECT r FROM " + Report.class.getSimpleName() + " r";
        List<Report> reports = session.createQuery(query, Report.class).getResultList();
        List<ReportResponse> reportResponses = new ArrayList<>();
        for (Report report : reports) {
            ReportResponse reportResponse = new ReportResponse(report.getId(), report.getName(),
                    report.getTask().getName(), report.getTaskEmployee().getName());
            reportResponses.add(reportResponse);
        }
        return reportResponses;
    }

    public ReportResponse findById(Session session, Integer id) {
        String query = "SELECT r FROM " + Report.class.getSimpleName() + " r";
        Report report = session.createQuery(query, Report.class).getSingleResult();
        return new ReportResponse(report.getId(), report.getName(),
                report.getTask().getName(), report.getTaskEmployee().getName());
    }

    public void deleteById(Session session, Integer id) {
        Transaction transaction = session.beginTransaction();
        Report report = session.get(Report.class, id);
        session.remove(report);
        transaction.commit();
    }

    public void save(Session session, ReportReceive r) {
        Transaction transaction = session.beginTransaction();
        Task task = session.get(Task.class, r.getTaskId());
        Report report = new Report(r.getName(), task, task.getEmployee());
        session.persist(report);
        transaction.commit();
    }

    public void update(Session session, ReportReceive r, Integer id) {
        Transaction transaction = session.beginTransaction();
        Report report = session.get(Report.class, id);
        Task task = session.get(Task.class, r.getTaskId());
        report.setName(r.getName());
        report.setTask(task);
        report.setTaskEmployee(task.getEmployee());
        session.persist(report);
        transaction.commit();
    }
}
