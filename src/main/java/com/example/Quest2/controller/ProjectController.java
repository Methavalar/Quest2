package com.example.Quest2.controller;

import com.example.Quest2.dto.ProjectHasEmployeeReceive;
import com.example.Quest2.dto.ProjectHasEmployeeResponse;
import com.example.Quest2.dto.ProjectResponse;
import com.example.Quest2.model.Project;
import com.example.Quest2.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project")
    public ResponseEntity<List<ProjectResponse>> findAll() {
        List<ProjectResponse> projects = projectService.findAll();
        if (projects == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectResponse> findById(@PathVariable Integer id) {
        ProjectResponse project = projectService.findById(id);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        projectService.deleteById(id);
        return ResponseEntity.ok("Project delete from the database");
    }

    @PostMapping("/project")
    public ResponseEntity<String> save(@RequestBody Project p) {
        projectService.save(p);
        return ResponseEntity.ok("Project saved from the database");
    }

    @PutMapping("/project/{id}")
    public ResponseEntity<String> update(@RequestBody Project p, @PathVariable Integer id) {
        projectService.update(p, id);
        return ResponseEntity.ok("Project updated from the database");
    }
    @GetMapping("/project_employee")
    public ResponseEntity<List<ProjectHasEmployeeResponse>> getProjectsEmployees() {
        List<ProjectHasEmployeeResponse> projectHasEmployeeResponseList = projectService.getProjectsEmployees();
        if (projectHasEmployeeResponseList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projectHasEmployeeResponseList);
    }

    @PostMapping("/project_employee")
    public ResponseEntity<String> saveProjectEmployee(@RequestBody ProjectHasEmployeeReceive projectHasEmployeeReceive) {
        projectService.saveProjectEmployee(projectHasEmployeeReceive);
        return ResponseEntity.ok("ProjectHasEmployee saved from the database");
    }

    @DeleteMapping("/project_employee")
    public ResponseEntity<String> deleteProjectEmployee(@RequestBody ProjectHasEmployeeReceive projectHasEmployeeReceive) {
        projectService.deleteProjectEmployee(projectHasEmployeeReceive);
        return ResponseEntity.ok("ProjectHasEmployee delete from the database");
    }
}
