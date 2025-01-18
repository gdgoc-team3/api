package com.team3.gdgoc.task;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_check_status")
@Getter
@NoArgsConstructor
public class TaskCheckStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "check_date", nullable = false)
    private LocalDate checkDate;

    @Column(name = "is_completed", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isCompleted;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    private TaskCheckStatusEntity(Long id, Long taskId, LocalDate checkDate, Boolean isCompleted, LocalDateTime createdAt) {
        this.id = id;
        this.taskId = taskId;
        this.checkDate = checkDate;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
    }
}