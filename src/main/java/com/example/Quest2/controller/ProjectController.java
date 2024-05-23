package com.example.Quest2.controller;

import com.example.Quest2.dto.ProjectHasEmployeeReceive;
import com.example.Quest2.dto.ProjectHasEmployeeResponse;
import com.example.Quest2.dto.ProjectResponse;
import com.example.Quest2.model.Project;
import com.example.Quest2.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project")
    public List<ProjectResponse> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/project/{id}")
    public ProjectResponse findById(@PathVariable Integer id) {
        return projectService.findById(id);
    }

    @DeleteMapping("/project/{id}")
    public String deleteById(@PathVariable Integer id) {
        projectService.deleteById(id);
        return "Project delete from the database";
    }

    @PostMapping("/project")
    public String save(@RequestBody Project p) {
        projectService.save(p);
        return "Project saved successfully";
    }

    @PutMapping("/project/{id}")
    public String update(@RequestBody Project p, @PathVariable Integer id) {
        projectService.update(p, id);
        return "Project updated successfully";
    }
    @GetMapping("/project_employee")
    public List<ProjectHasEmployeeResponse> getProjectsEmployees() {
        return projectService.getProjectsEmployees();
    }

    @PostMapping("/project_employee")
    public String saveProjectEmployee(@RequestBody ProjectHasEmployeeReceive projectHasEmployeeReceive) {
        projectService.saveProjectEmployee(projectHasEmployeeReceive);
        return "ProjectHasEmployee saved successfully";
    }

    @DeleteMapping("/project_employee")
    public String deleteProjectEmployee(@RequestBody ProjectHasEmployeeReceive projectHasEmployeeReceive) {
        projectService.deleteProjectEmployee(projectHasEmployeeReceive);
        return "ProjectHasEmployee delete from the database";
    }
}
