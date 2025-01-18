package com.team3.gdgoc.leaderboard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name="leaderboard")
public class LeaderboardEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="interest_group_id", nullable = false)
    private Long interestGroupId;

    @Column(name="process_ratio", nullable = false)
    private int processRatio;

    @Column(name="ranking", nullable = false)
    private int ranking;

    @Column(name="created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name="user_identity", nullable = false)
    private String userIdentity;

    @Builder
    public LeaderboardEntity(Long id, Long interestGroupId, int processRatio, int ranking, LocalDate createdAt, String userIdentity){
        this.id =id;
        this.interestGroupId = interestGroupId;
        this.processRatio = processRatio;
        this.ranking = ranking;
        this.createdAt = createdAt;
        this.userIdentity = userIdentity;
    }
}
