package com.team3.gdgoc.user;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddUserRequest {
    private LocalDate birthDate;
    private String nickname;
    private String userIdentity;
    private String major;
    private String desiredJob;
    private int targetEmploymentPeriod;

    @Builder
    public AddUserRequest(LocalDate birthDate, String nickname, String userIdentity, String major, String desiredJob, int targetEmploymentPeriod) {
        this.birthDate = birthDate;
        this.nickname = nickname;
        this.userIdentity = userIdentity;
        this.major = major;
        this.desiredJob = desiredJob;
        this.targetEmploymentPeriod = targetEmploymentPeriod;
    }
}
