package com.team3.gdgoc.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "identity", nullable = false, length = 16)
    private String identity;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "nickname", nullable = false, length = 255)
    private String nickname;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    public UserEntity(Long id, String identity, LocalDate birthDate, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.identity = identity;
        this.birthDate = birthDate;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }
}
