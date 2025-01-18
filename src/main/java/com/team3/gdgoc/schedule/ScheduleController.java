package com.team3.gdgoc.schedule;

import com.team3.gdgoc.common.ApiResponse;
import com.team3.gdgoc.user.MonthlyProgressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/")
    public ApiResponse<List<ScheduleResponse>> getSchedule(
            @RequestParam String userIdentity,
            @RequestParam String year,
            @RequestParam String month
            ) {

        return ApiResponse.success(scheduleService.getScheduleList(userIdentity, year, month));
    }

    @GetMapping("/progress")
    public ApiResponse<List<MonthlyProgressResponse>> getProgress(
            @RequestParam String userIdentity,
            @RequestParam String year,
            @RequestParam String month
    ) {
        return ApiResponse.success(scheduleService.getMonthlyProgress(userIdentity, year, month));
    }

    @GetMapping("/feedback/{scheduleId}")
    public ApiResponse<Boolean> getFeedback(
            @RequestParam String userIdentity,
            @RequestParam String year,
            @RequestParam String month,
            @PathVariable String scheduleId) {
        return ApiResponse.success(true);
    }

    @GetMapping("/hasSchedule/{userIdentity}")
    public ApiResponse<Boolean> hasSchedule(@PathVariable String userIdentity,
                                            // yyyy-MM-dd
                                            @RequestParam String date)  {
        return ApiResponse.success(scheduleService.hasSchedule(userIdentity,date));
    }


    @PostMapping("/")
    public ApiResponse<ScheduleResponse> addSchedule(@RequestBody AddScheduleRequest request) {
        ScheduleResponse response = scheduleService.addSchedule(request);
        return ApiResponse.success(response);
    }



}
