package com.example.Quest2.service;

import com.example.Quest2.dto.TaskReceive;
import com.example.Quest2.dto.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> findAll();

    TaskResponse findById(Integer id);

    void deleteById(Integer id);

    void save(TaskReceive t);

    void update(TaskReceive t, Integer id);
}
