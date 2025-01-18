package com.team3.gdgoc.user;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MonthlyProgressResponse {
    private int day;  // 날짜
    private int progress; // 진행도
}
