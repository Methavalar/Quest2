package com.example.Quest2.service.impl;

import com.example.Quest2.dto.TaskReceive;
import com.example.Quest2.dto.TaskResponse;
import com.example.Quest2.repository.TaskRepository;
import com.example.Quest2.service.TaskService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private static SessionFactory sessionFactory;
    private static Session session;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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
    public List<TaskResponse> findAll() {
        createHibernateSession();
        List<TaskResponse> tasks = taskRepository.findAll(session);
        session.close();
        sessionFactory.close();
        return tasks;
    }

    @Override
    public TaskResponse findById(Integer id) {
        createHibernateSession();
        TaskResponse task = taskRepository.findById(session, id);
        session.close();
        sessionFactory.close();
        return task;
    }

    @Override
    public void deleteById(Integer id) {
        createHibernateSession();
        taskRepository.deleteById(session, id);
        session.close();
        sessionFactory.close();
    }

    @Override
    public void save(TaskReceive t) {
        createHibernateSession();
        taskRepository.save(session, t);
        session.close();
        sessionFactory.close();
    }

    @Override
    public void update(TaskReceive t, Integer id) {
        createHibernateSession();
        taskRepository.update(session, t, id);
        session.close();
        sessionFactory.close();
    }
}
