package com.example.Quest2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "project_has_employee",
    joinColumns = {@JoinColumn(name = "Project_id")},
    inverseJoinColumns = {@JoinColumn(name = "Employee_id")})
    private List<Employee> employees = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }
}
