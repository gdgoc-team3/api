package com.team3.gdgoc.interest;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "interest")
public class InterestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_identity", length = 16, nullable = false)
    private String userIdentity;

    @Column(name = "major", length = 100, nullable = false)
    private String major;

    @Column(name = "desired_job", length = 100, nullable = false)
    private String desiredJob;

    @Column(name = "target_employment_period", nullable = false)
    private int targetEmploymentPeriod;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    public InterestEntity(Long id, String userIdentity, String major, String desiredJob, int targetEmploymentPeriod, LocalDateTime createdAt) {
        this.id = id;
        this.userIdentity = userIdentity;
        this.major = major;
        this.desiredJob = desiredJob;
        this.targetEmploymentPeriod = targetEmploymentPeriod;
        this.createdAt = createdAt;
    }
}
