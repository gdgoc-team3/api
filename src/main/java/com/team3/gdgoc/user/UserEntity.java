package com.team3.gdgoc.user;

import com.team3.gdgoc.interest.InterestEntity;
import jakarta.persistence.*;
import java.util.List;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterestEntity> interests;

    @Builder
    public UserEntity(Long id, String identity, LocalDate birthDate, String nickname, LocalDateTime createdAt, List<InterestEntity> interests) {
        this.id = id;
        this.identity = identity;
        this.birthDate = birthDate;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.interests = interests;
    }
}
