package com.example.Quest2.controller;

import com.example.Quest2.dto.TaskReceive;
import com.example.Quest2.dto.TaskResponse;
import com.example.Quest2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    public List<TaskResponse> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/task/{id}")
    public TaskResponse findById(@PathVariable Integer id) {
        return taskService.findById(id);
    }

    @DeleteMapping("/task/{id}")
    public String deleteById(@PathVariable Integer id) {
        taskService.deleteById(id);
        return "Task delete from the database";
    }

    @PostMapping("/task")
    public String save(@RequestBody TaskReceive t) {
        taskService.save(t);
        return "Task saved successfully";
    }

    @PutMapping("/task/{id}")
    public String update(@RequestBody TaskReceive t, @PathVariable Integer id) {
        taskService.update(t, id);
        return "Task updated successfully";
    }
}
