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

        String scheduleDate = request.getScheduleStartDate() + "-" + request.getScheduleEndDate();

        String birthDateFormatted = request.getBirthDate().replace("-", "");

        List<String> query = List.of(
                birthDateFormatted,
                request.getMajor(),
                request.getDesiredJob(),
                formatEmploymentPeriod(Integer.parseInt(request.getTargetEmploymentPeriod())),
                request.getScheduleTitle(),
                scheduleDate,
                request.getMustDoTasks(),
                request.getRequirements()
        );

        return aiClient.getSchedules(query);
    }

    private String formatEmploymentPeriod(int months) {
        int years = months / 12;
        int remainingMonths = months % 12;
        return String.format("%03d", remainingMonths, years * 100 + remainingMonths);
    }

}
