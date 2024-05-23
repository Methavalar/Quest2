package com.example.Quest2.controller;

import com.example.Quest2.dto.EmployeeResponse;
import com.example.Quest2.model.Employee;
import com.example.Quest2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public List<EmployeeResponse> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public EmployeeResponse findById(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteById(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return "Employee delete from the database";
    }

    @PostMapping("/employee")
    public String save(@RequestBody Employee e) {
        employeeService.save(e);
        return "Employee saved successfully";
    }

    @PutMapping("/employee/{id}")
    public String update(@RequestBody Employee e, @PathVariable Integer id) {
        employeeService.update(e, id);
        return "Employee updated successfully";
    }
}
