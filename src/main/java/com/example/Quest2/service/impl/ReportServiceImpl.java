package com.example.Quest2.service.impl;

import com.example.Quest2.dto.ReportReceive;
import com.example.Quest2.dto.ReportResponse;
import com.example.Quest2.repository.ReportRepository;
import com.example.Quest2.service.ReportService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private static SessionFactory sessionFactory;
    private static Session session;
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    private void createHibernateSession()
    {
        try {
            try {
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml").build();
                Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
            session = sessionFactory.openSession();
        } catch (Exception e) {
            System.err.println("Failed to create session object." + e);
            throw new RuntimeException();
        }
    }

    @Override
    public List<ReportResponse> findAll() {
        createHibernateSession();
        List<ReportResponse> reports = reportRepository.findAll(session);
        session.close();
        sessionFactory.close();
        return reports;
    }

    @Override
    public ReportResponse findById(Integer id) {
        createHibernateSession();
        ReportResponse report = reportRepository.findById(session, id);
        session.close();
        sessionFactory.close();
        return report;
    }

    @Override
    public void deleteById(Integer id) {
        createHibernateSession();
        reportRepository.deleteById(session, id);
        session.close();
        sessionFactory.close();
    }

    @Override
    public void save(ReportReceive r) {
        createHibernateSession();
        reportRepository.save(session, r);
        session.close();
        sessionFactory.close();
    }

    @Override
    public void update(ReportReceive r, Integer id) {
        createHibernateSession();
        reportRepository.update(session, r, id);
        session.close();
        sessionFactory.close();
    }
}
