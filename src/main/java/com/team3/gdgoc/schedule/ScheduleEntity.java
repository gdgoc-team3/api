package com.team3.gdgoc.schedule;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "interest_id", nullable = false)
    private Long interestId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "must_do_tasks", nullable = false, columnDefinition = "TEXT")
    private String mustDoTasks;

    @Column(name = "schedule_requirements", nullable = false, columnDefinition = "TEXT")
    private String scheduleRequirements;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    private ScheduleEntity(Long id, Long userId, Long interestId, String title, LocalDate startDate, LocalDate endDate, String mustDoTasks, String scheduleRequirements, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.interestId = interestId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mustDoTasks = mustDoTasks;
        this.scheduleRequirements = scheduleRequirements;
        this.createdAt = createdAt;
    }
}
