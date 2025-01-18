package com.team3.gdgoc.leaderboard;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LeaderboardRequest {
    private Long interestGroupId;
    private int processRatio;
    private int ranking;
    private String userIdentity;


    @Builder
    public LeaderboardRequest( Long interestGroupId, int processRatio, int ranking, String userIdentity) {
        this.interestGroupId = interestGroupId;
        this.processRatio = processRatio;
        this.ranking = ranking;
        this.userIdentity = userIdentity;
    }
}
