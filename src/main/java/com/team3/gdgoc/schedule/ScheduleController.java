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
    public ApiResponse<ScheduleResponse> getSchedule(int scheduleId) {
        TaskResponse task1 = TaskResponse.builder()
                .taskId(1)
                .title("이력서 작성 연습")
                .isCompleted(false)
                .startDate(
                        TaskDateResponse.builder()
                                .year(2021)
                                .month(8)
                                .day(1)
                                .hour(10)
                                .minute(0)
                                .build()
                )
                .endDate(
                        TaskDateResponse.builder()
                                .year(2021)
                                .month(8)
                                .day(1)
                                .hour(10)
                                .minute(30)
                                .build()
                )
                .build();

        TaskResponse task2 = TaskResponse.builder()
                .taskId(1)
                .title("인프런 동영상보기")
                .isCompleted(false)
                .startDate(
                        TaskDateResponse.builder()
                                .year(2021)
                                .month(8)
                                .day(1)
                                .hour(11)
                                .minute(0)
                                .build()
                )
                .endDate(
                        TaskDateResponse.builder()
                                .year(2021)
                                .month(8)
                                .day(1)
                                .hour(11)
                                .minute(30)
                                .build()
                )
                .build();

        ScheduleResponse response = ScheduleResponse.builder()
                .scheduleId(scheduleId)
                .title("취업준비아자아자ㅎㅎ")
                .startDate("2021-08-01")
                .endDate("2021-08-31")
                .mustDoTasks("이력서 작성, 자소서 작성, 면접 준비")
                .requirements("취업 준비")
                .tasks(List.of(task1,task2))
                .build();

        return ApiResponse.success(response);
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
