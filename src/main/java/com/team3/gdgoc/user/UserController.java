package com.team3.gdgoc.user;

import com.team3.gdgoc.common.ApiResponse;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
//    private final UserService userService;

    @GetMapping("/my/{userIdentity}")
    public ApiResponse<MyInfoResponse> getMyInfo(@PathVariable String userIdentity,
                                                   @RequestParam int year, @RequestParam int month) {

        // 임시데이터
        String nickname = "김시현";
        int ranking = 3;
        int processRatio = 85;
        int totalUsers = 100;

        // 임시 날짜별 진행도
        List<MonthlyProgressResponse> monthlyProgressResponses = List.of(
                MonthlyProgressResponse.builder().day(1).progress(60).build(),
                MonthlyProgressResponse.builder().day(2).progress(75).build(),
                MonthlyProgressResponse.builder().day(3).progress(85).build(),
                MonthlyProgressResponse.builder().day(4).progress(90).build(),
                MonthlyProgressResponse.builder().day(5).progress(50).build()
        );

        // MyInfoResponse 객체 생성
        MyInfoResponse response = MyInfoResponse.builder()
                .nickname(nickname)
                .ranking(ranking)
                .processRatio(processRatio)
                .totalUsers(totalUsers)
                .year(year)
                .month(month)
                .monthlyProgressResponses(monthlyProgressResponses)
                .build();

        return ApiResponse.success(response);
    }

    @GetMapping("/{userIdentity}")
    public ApiResponse<UserInfoResponse> getUserInfo(@PathVariable String userIdentity) {
        UserInfoResponse response = UserInfoResponse.builder()
                .birthDate(LocalDate.of(1998, 1, 1))
                .nickname("개발자")
                .userIdentity(userIdentity)
                .major("컴퓨터과학과")
                .desiredJob("백엔드")
                .targetEmploymentPeriod(3)
                .build();

        return ApiResponse.success(response);
    }

    @PostMapping("")
    public ApiResponse<UserInfoResponse> addUser(@RequestBody AddUserRequest request) {
        UserInfoResponse response = UserInfoResponse.builder()
                .birthDate(request.getBirthDate())
                .nickname(request.getNickname())
                .userIdentity(request.getUserIdentity())
                .major(request.getMajor())
                .desiredJob(request.getDesiredJob())
                .targetEmploymentPeriod(request.getTargetEmploymentPeriod())
                .build();

        return ApiResponse.success(response);
    }

}
