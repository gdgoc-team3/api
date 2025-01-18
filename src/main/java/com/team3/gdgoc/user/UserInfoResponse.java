package com.team3.gdgoc.user;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class UserInfoResponse {
    private Long userId;
    private String identity;
    private LocalDate birthDate;
    private String nickname;
    private String major;
    private String desiredJob;
    private int targetEmploymentPeriod;

    @Builder
    public UserInfoResponse(Long userId, String identity, LocalDate birthDate, String nickname, String major, String desiredJob, int targetEmploymentPeriod) {
        this.userId = userId;
        this.identity = identity;
        this.birthDate = birthDate;
        this.nickname = nickname;
        this.major = major;
        this.desiredJob = desiredJob;
        this.targetEmploymentPeriod = targetEmploymentPeriod;
    }
}
