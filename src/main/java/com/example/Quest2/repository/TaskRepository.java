package com.example.Quest2.repository;

import com.example.Quest2.dto.TaskReceive;
import com.example.Quest2.dto.TaskResponse;
import com.example.Quest2.model.Employee;
import com.example.Quest2.model.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    public List<TaskResponse> findAll(Session session) {
        String query = "SELECT t FROM " + Task.class.getSimpleName() + " t";
        List<Task> tasks = session.createQuery(query, Task.class).getResultList();
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task tsk : tasks) {
            TaskResponse taskResponse = new TaskResponse(tsk.getId(), tsk.getName(), tsk.getEmployee().getName());
            taskResponses.add(taskResponse);
        }
        return taskResponses;
    }

    public TaskResponse findById(Session session, Integer id) {
        String query = "SELECT t FROM " + Task.class.getSimpleName() + " t";
        Task task = session.createQuery(query, Task.class).getSingleResult();
        return new TaskResponse(task.getId(), task.getName(), task.getEmployee().getName());
    }

    public void deleteById(Session session, Integer id) {
        Transaction transaction = session.beginTransaction();
        Task task = session.get(Task.class, id);
        session.remove(task);
        transaction.commit();
    }

    public void save(Session session, TaskReceive t) {
        Transaction transaction = session.beginTransaction();
        Task task = new Task(t.getName(), session.get(Employee.class, t.getEmployeeId()));
        session.persist(task);
        transaction.commit();
    }

    public void update(Session session, TaskReceive t, Integer id) {
        Transaction transaction = session.beginTransaction();
        Task task = session.get(Task.class, id);
        task.setName(t.getName());
        task.setEmployee(session.get(Employee.class, t.getEmployeeId()));
        session.persist(task);
        transaction.commit();
    }
}
