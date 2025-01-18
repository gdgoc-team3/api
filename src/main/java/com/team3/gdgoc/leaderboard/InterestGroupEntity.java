//package com.team3.gdgoc.leaderboard;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import java.time.LocalDate;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "interest_group")
//@Getter
//@NoArgsConstructor
//public class InterestGroupEntity {
//    @Id
//    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false, updatable = false)
//    private Long id;
//
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
//
//    @Column(name = "schedule_id", nullable = false)
//    private Long scheduleId;
//
//    @Column(name = "group_name", nullable = false, length = 255)
//    private String groupName;
//
//    @Column(name = "created_at", nullable = false)
//    private LocalDate createdAt;
//
//    @Builder
//    public InterestGroupEntity(Long id, Long userId, Long scheduleId, String groupName, LocalDate createdAt) {
//        this.id = id;
//        this.userId = userId;
//        this.scheduleId = scheduleId;
//        this.groupName = groupName;
//        this.createdAt = createdAt;
//    }
//}
