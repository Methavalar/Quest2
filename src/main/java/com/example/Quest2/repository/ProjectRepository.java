package com.example.Quest2.repository;

import com.example.Quest2.dto.ProjectHasEmployeeReceive;
import com.example.Quest2.dto.ProjectHasEmployeeResponse;
import com.example.Quest2.dto.ProjectResponse;
import com.example.Quest2.model.Employee;
import com.example.Quest2.model.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {


    public List<ProjectResponse> findAll(Session session) {
        String query = "SELECT p FROM " + Project.class.getSimpleName() + " p";
        List<Project> projects = session.createQuery(query, Project.class).getResultList();
        List<ProjectResponse> projectResponseList = new ArrayList<>();
        for (Project project : projects) {
            ProjectResponse projectResponse = new ProjectResponse(project.getId(), project.getName());
            projectResponseList.add(projectResponse);
        }
        return projectResponseList;
    }

    public ProjectResponse findById(Session session, Integer id) {
        String query = "SELECT p FROM " + Project.class.getSimpleName() + " p WHERE id = " + id;
        Project project = session.createQuery(query, Project.class).getSingleResult();
        return new ProjectResponse(project.getId(), project.getName());
    }

    public void deleteById(Session session, Integer id) {
        Transaction transaction = session.beginTransaction();
        Project project = session.get(Project.class, id);
        session.remove(project);
        transaction.commit();
    }

    public void save(Session session, Project p) {
        Transaction transaction = session.beginTransaction();
        session.persist(p);
        transaction.commit();
    }

    public void update(Session session, Project p, Integer id) {
        Transaction transaction = session.beginTransaction();
        Project project = session.get(Project.class, id);
        project.setName(p.getName());
        session.persist(project);
        transaction.commit();
    }

    public List<ProjectHasEmployeeResponse> findAllProjectEmployee(Session session) {
        String query = "SELECT p FROM " + Project.class.getSimpleName() + " p";
        List<Project> projects = session.createQuery(query, Project.class).getResultList();
        List<ProjectHasEmployeeResponse> projectHasEmployeeList = new ArrayList<>();
        for (Project project : projects) {
            List<Employee> employees = project.getEmployees();
            for (Employee employee : employees) {
                ProjectHasEmployeeResponse projectHasEmployee = new ProjectHasEmployeeResponse(project.getName(),
                        employee.getName());
                projectHasEmployeeList.add(projectHasEmployee);
            }
        }
        return projectHasEmployeeList;
    }

    public void saveProjectEmployee(Session session, ProjectHasEmployeeReceive projectHasEmployeeReceive) {
        Transaction transaction = session.beginTransaction();
        Project project = session.get(Project.class, projectHasEmployeeReceive.getProjectId());
        Employee employee = session.get(Employee.class, projectHasEmployeeReceive.getEmployeeId());
        project.getEmployees().add(employee);
        transaction.commit();
    }

    public void deleteProjectEmployee(Session session, ProjectHasEmployeeReceive projectHasEmployeeReceive) {
        Transaction transaction = session.beginTransaction();
        Project project = session.get(Project.class, projectHasEmployeeReceive.getProjectId());
        Employee employee = session.get(Employee.class, projectHasEmployeeReceive.getEmployeeId());
        project.getEmployees().remove(employee);
        transaction.commit();
    }
}
