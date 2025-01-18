package com.team3.gdgoc.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddScheduleRequest {

    @Schema(description = "사용자 식별자(16자리랜덤문자열)", example = "abcdfqweaxvaweda")
    private String userIdentity;

    @Schema(description = "일정 제목", example = "이력서 작성")
    private String title;

    @Schema(description = "시작일", example = "2021-08-01")
    private String startDate;

    @Schema(description = "종료일", example = "2021-08-02")
    private String endDate;

    @Schema(description = "필수할 일", example = "이력서 작성")
    private String mustDoTasks;

    @Schema(description = "일정 요구사항", example = "이력서 작성")
    private String requirements;

    @Builder
    public AddScheduleRequest(String userIdentity, String title, String startDate, String endDate, String mustDoTasks, String requirements) {
        this.userIdentity = userIdentity;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mustDoTasks = mustDoTasks;
        this.requirements = requirements;
    }
}
