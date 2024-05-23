package com.example.Quest2.controller;

import com.example.Quest2.dto.TaskReceive;
import com.example.Quest2.dto.TaskResponse;
import com.example.Quest2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    public ResponseEntity<List<TaskResponse>> findAll() {
        List<TaskResponse> tasks = taskService.findAll();
        if (tasks == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Integer id) {
        TaskResponse task = taskService.findById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        taskService.deleteById(id);
        return ResponseEntity.ok("Task delete from the database");
    }

    @PostMapping("/task")
    public ResponseEntity<String> save(@RequestBody TaskReceive t) {
        taskService.save(t);
        return ResponseEntity.ok("Task saved from the database");
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<String> update(@RequestBody TaskReceive t, @PathVariable Integer id) {
        taskService.update(t, id);
        return ResponseEntity.ok("Task updated from the database");
    }
}
