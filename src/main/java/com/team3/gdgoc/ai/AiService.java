package com.team3.gdgoc.ai;

import com.team3.gdgoc.ai.client.AiClient;
import com.team3.gdgoc.ai.client.FeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiService {

    private final AiClient aiClient;

    public FeedbackResponse getSchedules(GetAiScheduleRequest request) {

        List<String> query = List.of(
                request.getBirthDate(),
                request.getMajor(),
                request.getDesiredJob(),
                request.getTargetEmploymentPeriod(),
                request.getScheduleTitle(),
                request.getScheduleDate(),
                request.getScheduleTime(),
                request.getMustDoTasks(),
                request.getRequirements()
        );

        return aiClient.getSchedules(query);
    }

}
