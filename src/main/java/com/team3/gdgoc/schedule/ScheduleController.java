package com.team3.gdgoc.schedule;

import com.team3.gdgoc.common.ApiResponse;
import com.team3.gdgoc.task.TaskDateResponse;
import com.team3.gdgoc.task.TaskResponse;
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

    @PostMapping("/")
    public ApiResponse<ScheduleResponse> addSchedule(@RequestBody AddScheduleRequest request) {
        ScheduleResponse response = scheduleService.addSchedule(request);
        return ApiResponse.success(response);
    }

    @GetMapping("/hasSchedule/{userIdentity}")
    public ApiResponse<Boolean> hasSchedule(@PathVariable String userIdentity,
                                            // yyyy-MM-dd
                                            @RequestParam String date)  {
        return ApiResponse.success(scheduleService.hasSchedule(userIdentity,date));
    }

}
