package com.team3.gdgoc.user;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class UserInfoResponse {
    private LocalDate birthDate;
    private String nickname;
    private String userIdentity;
    private String major;
    private String desiredJob;
    private int targetEmploymentPeriod;

    @Builder
    public UserInfoResponse(LocalDate birthDate,String nickname,String userIdentity, String major, String desiredJob, int targetEmploymentPeriod) {
        this.birthDate= birthDate;
        this.nickname = nickname;
        this.userIdentity = userIdentity;
        this.major = major;
        this.desiredJob = desiredJob;
        this.targetEmploymentPeriod = targetEmploymentPeriod;
    }
}
