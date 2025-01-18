package com.team3.gdgoc.user;

import com.team3.gdgoc.common.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
@RestController
@RequestMapping("/user2")
public class User2Controller {

    @GetMapping("/{userIdentity}")
    public ApiResponse<MyInfoResponse> getUserInfo(@PathVariable String userIdentity,
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
}
