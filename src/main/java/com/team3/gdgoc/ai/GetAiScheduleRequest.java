package com.team3.gdgoc.ai;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAiScheduleRequest {

    private String birthDate;

    private String major;

    private String desiredJob;

    private String targetEmploymentPeriod;

    private String scheduleTitle;

    private String scheduleDate;

    private String scheduleTime;

    private String mustDoTasks;

    private String requirements;

    @Builder
    private GetAiScheduleRequest(String birthDate, String major, String desiredJob, String targetEmploymentPeriod, String scheduleTitle, String scheduleDate, String scheduleTime, String mustDoTasks, String requirements) {
        this.birthDate = birthDate;
        this.major = major;
        this.desiredJob = desiredJob;
        this.targetEmploymentPeriod = targetEmploymentPeriod;
        this.scheduleTitle = scheduleTitle;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.mustDoTasks = mustDoTasks;
        this.requirements = requirements;
    }
}
