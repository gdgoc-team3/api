package com.team3.gdgoc.user;

import com.team3.gdgoc.common.ApiResponse;
import com.team3.gdgoc.interest.InterestEntity;
import com.team3.gdgoc.interest.InterestService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final InterestService interestService;
    private final UserMyService userMyService;

    @GetMapping("/my/{userIdentity}")
    public ApiResponse<MyInfoResponse> getMyInfo(@PathVariable String userIdentity,
                                                   @RequestParam int year, @RequestParam int month) {
        // userService를 이용해 nickname 가져오기
        String nickname = userMyService.getNickname(userIdentity); // nickname 가져오

        // 임시데이터
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
        UserInfoResponse userInfo= userService.getUserInfoByIdentity(userIdentity);
        InterestEntity interestEntity = interestService.getInterest(userInfo.getUserId());
        if(interestEntity == null) {
            throw new IllegalArgumentException("관심사가 존재하지 않습니다.");
        }
        UserInfoResponse response = UserInfoResponse.builder()
                .userId(userInfo.getUserId())
                .identity(userInfo.getIdentity())
                .birthDate(userInfo.getBirthDate())
                .nickname(userInfo.getNickname())
                .major(interestEntity.getMajor())
                .targetEmploymentPeriod(interestEntity.getTargetEmploymentPeriod())
                .desiredJob(interestEntity.getDesiredJob())
                .build();


        return ApiResponse.success(response);
    }

    @PostMapping("")
    public ApiResponse<UserInfoResponse> createUser(@RequestBody AddUserRequest request) {
        UserInfoResponse response = userService.createUser(request);
        return ApiResponse.success(response);
    }

}
