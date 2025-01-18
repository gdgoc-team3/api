package com.team3.gdgoc.ai.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "aiClient", url = "http://ai.gdgoc-team3.site:8000")
public interface AiClient {

//    @GetMapping("/feedback/readInfo")
//    String getSchedules(@RequestParam String birthDate,
//                        @RequestParam String major,
//                        @RequestParam String desiredJob,
//                        @RequestParam String targetEmploymentPeriod,
//                        @RequestParam String scheduleTitle,
//                        @RequestParam String scheduleDate,
//                        @RequestParam String scheduleTime,
//                        @RequestParam String mustDoTasks,
//                        @RequestParam String requirements);

    @GetMapping("/feedback/readInfo")
    FeedbackResponse getSchedules(@RequestParam List<String> info);


}
