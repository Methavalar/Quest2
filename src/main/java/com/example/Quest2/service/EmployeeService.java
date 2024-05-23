package com.example.Quest2.service;

import com.example.Quest2.dto.EmployeeResponse;
import com.example.Quest2.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> findAll();

    EmployeeResponse findById(Integer id);

    void deleteById(Integer id);

    void save(Employee e);

    void update(Employee e, Integer id);
}
