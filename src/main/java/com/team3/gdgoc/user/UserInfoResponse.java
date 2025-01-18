package com.team3.gdgoc.user;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class MyInfoResponse {
    private String userIdentity;
    private String major;
    private String desiredJob;
    private int targetEmploymentPeriod;

    @Builder
    public MyInfoResponse( String userIdentity, String major, String desiredJob, int targetEmploymentPeriod) {
        this.userIdentity = userIdentity;
        this.major = major;
        this.desiredJob = desiredJob;
        this.targetEmploymentPeriod = targetEmploymentPeriod;
    }
}
