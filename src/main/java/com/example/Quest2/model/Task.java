package com.example.Quest2.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Employee_id", nullable = false, referencedColumnName = "id")
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL)
    protected Report report;

    public Task(String name, Employee employee) {
        this.name = name;
        this.employee = employee;
    }
}
