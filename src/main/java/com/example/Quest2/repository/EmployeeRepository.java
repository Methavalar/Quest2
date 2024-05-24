package com.example.Quest2.repository;

import com.example.Quest2.dto.EmployeeResponse;
import com.example.Quest2.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    public List<EmployeeResponse> findAll(Session session) {
        String query = "SELECT e FROM " + Employee.class.getSimpleName() + " e";
        List<Employee> employees = session.createQuery(query, Employee.class).getResultList();
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        for (Employee empl : employees) {
            EmployeeResponse employeeResponse = new EmployeeResponse(empl.getId(), empl.getName());
            employeeResponseList.add(employeeResponse);
        }
        return employeeResponseList;
    }

    public EmployeeResponse findById(Session session, Integer id) {
        String query = "SELECT e FROM " + Employee.class.getSimpleName() + " e WHERE id = " + id;
        Employee employee = session.createQuery(query, Employee.class).getSingleResult();
        return new EmployeeResponse(employee.getId(), employee.getName());
    }

    public void deleteById(Session session, Integer id) {
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.remove(employee);
        transaction.commit();
    }

    public void save(Session session, Employee e) {
        Transaction transaction = session.beginTransaction();
        session.persist(e);
        transaction.commit();
    }

    public void update(Session session, Employee e, Integer id) {
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        employee.setName(e.getName());
        session.persist(employee);
        transaction.commit();
    }
}
