package com.example.Quest2.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Task_id", nullable = false, referencedColumnName = "id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Task_Employee_id", nullable = false, referencedColumnName = "id")
    private Employee taskEmployee;

    public Report(String name, Task task, Employee taskEmployee) {
        this.name = name;
        this.task = task;
        this.taskEmployee = taskEmployee;
    }
}
