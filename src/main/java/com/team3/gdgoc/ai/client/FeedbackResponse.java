package com.team3.gdgoc.ai.client;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FeedbackResponse {
    private List<FeedbackTaskResponse> tasks;

    @Builder
    private FeedbackResponse(List<FeedbackTaskResponse> tasks) {
        this.tasks = tasks;
    }
}
