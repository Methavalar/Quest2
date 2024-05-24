package com.example.Quest2.service.impl;

import com.example.Quest2.dto.EmployeeResponse;
import com.example.Quest2.model.Employee;
import com.example.Quest2.repository.EmployeeRepository;
import com.example.Quest2.service.EmployeeService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static SessionFactory sessionFactory;
    private static Session session;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
    public List<EmployeeResponse> findAll() {
        createHibernateSession();
        List<EmployeeResponse> employees = employeeRepository.findAll(session);
        session.close();
        sessionFactory.close();
        return employees;
    }

    @Override
    public EmployeeResponse findById(Integer id) {
        createHibernateSession();
        EmployeeResponse employee = employeeRepository.findById(session, id);
        session.close();
        sessionFactory.close();
        return employee;
    }

    @Override
    public void deleteById(Integer id) {
        createHibernateSession();
        employeeRepository.deleteById(session, id);
        session.close();
        sessionFactory.close();
    }

    @Override
    public void save(Employee e) {
        createHibernateSession();
        employeeRepository.save(session, e);
        session.close();
        sessionFactory.close();
    }

    @Override
    public void update(Employee e, Integer id) {
        createHibernateSession();
        employeeRepository.update(session, e, id);
        session.close();
        sessionFactory.close();
    }
}
