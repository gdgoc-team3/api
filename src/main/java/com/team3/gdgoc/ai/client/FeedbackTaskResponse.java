package com.team3.gdgoc.ai.client;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedbackTaskResponse {
    private String title;

    private FeedbackDateTimeInfoResponse startDate;

    private FeedbackDateTimeInfoResponse endDate;

    @Builder
    private FeedbackTaskResponse(String title, FeedbackDateTimeInfoResponse startDate, FeedbackDateTimeInfoResponse endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
