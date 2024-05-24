package com.example.Quest2.controller;

import com.example.Quest2.dto.EmployeeResponse;
import com.example.Quest2.model.Employee;
import com.example.Quest2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeResponse>> findAll() {
        List<EmployeeResponse> employees = employeeService.findAll();
        if (employees == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable Integer id) {
        EmployeeResponse employee = employeeService.findById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok("Employee delete from the database");
    }

    @PostMapping("/employee")
    public ResponseEntity<String> save(@RequestBody Employee e) {
        employeeService.save(e);
        return ResponseEntity.ok("Employee saved successfully");
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<String> update(@RequestBody Employee e, @PathVariable Integer id) {
        employeeService.update(e, id);
        return ResponseEntity.ok("Employee updated successfully");
    }
}
