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
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    protected List<Task> taskList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taskEmployee", cascade = CascadeType.ALL)
    protected List<Report> reportList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "project_has_employee",
            joinColumns = {@JoinColumn(name = "Employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "Project_id")})
    private List<Project> projects = new ArrayList<>();

    public Employee(String name) {
        this.name = name;
    }
}
