package com.example.Quest2.service.impl;

import com.example.Quest2.dto.ProjectHasEmployeeReceive;
import com.example.Quest2.dto.ProjectHasEmployeeResponse;
import com.example.Quest2.dto.ProjectResponse;
import com.example.Quest2.model.Project;
import com.example.Quest2.repository.ProjectRepository;
import com.example.Quest2.service.ProjectService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static SessionFactory sessionFactory;
    private static Session session;
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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
    public List<ProjectResponse> findAll() {
        createHibernateSession();
        List<ProjectResponse> projects = projectRepository.findAll(session);
        session.close();
        sessionFactory.close();
        return projects;
    }

    @Override
    public ProjectResponse findById(Integer id) {
        createHibernateSession();
        ProjectResponse project = projectRepository.findById(session, id);
        session.close();
        sessionFactory.close();
        return project;
    }

    @Override
    public void deleteById(Integer id) {
        createHibernateSession();
        projectRepository.deleteById(session, id);
        session.close();
        sessionFactory.close();
    }

    @Override
    public void save(Project p) {
        createHibernateSession();
        projectRepository.save(session, p);
        session.close();
        sessionFactory.close();
    }

    @Override
    public void update(Project p, Integer id) {
        createHibernateSession();
        projectRepository.update(session, p, id);
        session.close();
        sessionFactory.close();
    }

    @Override
    public List<ProjectHasEmployeeResponse> getProjectsEmployees() {
        createHibernateSession();
        List<ProjectHasEmployeeResponse> projectHasEmployeeList = projectRepository.findAllProjectEmployee(session);
        session.close();
        sessionFactory.close();
        return projectHasEmployeeList;
    }

    @Override
    public void saveProjectEmployee(ProjectHasEmployeeReceive projectHasEmployeeReceive) {
        createHibernateSession();
        projectRepository.saveProjectEmployee(session, projectHasEmployeeReceive);
        session.close();
        sessionFactory.close();
    }

    @Override
    public void deleteProjectEmployee(ProjectHasEmployeeReceive projectHasEmployeeReceive) {
        createHibernateSession();
        projectRepository.deleteProjectEmployee(session, projectHasEmployeeReceive);
        session.close();
        sessionFactory.close();
    }

}
