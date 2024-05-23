package com.example.Quest2.service;

import com.example.Quest2.dto.ProjectHasEmployeeReceive;
import com.example.Quest2.dto.ProjectHasEmployeeResponse;
import com.example.Quest2.dto.ProjectResponse;
import com.example.Quest2.model.Project;

import java.util.List;

public interface ProjectService {

    List<ProjectResponse> findAll();

    ProjectResponse findById(Integer id);

    void deleteById(Integer id);

    void save(Project p);

    void update(Project p, Integer id);

    List<ProjectHasEmployeeResponse> getProjectsEmployees();

    void saveProjectEmployee(ProjectHasEmployeeReceive projectHasEmployeeReceive);

    void deleteProjectEmployee(ProjectHasEmployeeReceive projectHasEmployeeReceive);
}
