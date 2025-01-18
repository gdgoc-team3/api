package com.team3.gdgoc.user;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyInfoResponse {
    private String nickname;            // 유저 닉네임
    private int ranking;                // 유저의 등수
    private double processRatio;           // 유저의 전체 진행도 (0~100)
    private int totalUsers;             // 전체 유저 수
    private String year;                   // 요청된 년도
    private String month;                  // 요청된 월
    private List<MonthlyProgressResponse> monthlyProgressResponses; // 날짜별 진행도 (key: 날짜, value: 진행도)


    @Builder
    public MyInfoResponse(String nickname, int ranking, double processRatio, int totalUsers,
                          String year, String month,List<MonthlyProgressResponse> monthlyProgressResponses) {
        this.nickname = nickname;
        this.ranking = ranking;
        this.processRatio = processRatio;
        this.totalUsers = totalUsers;
        this.year = year;
        this.month = month;
        this.monthlyProgressResponses = monthlyProgressResponses;
    }
}