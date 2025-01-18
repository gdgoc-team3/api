package com.team3.gdgoc.leaderboard;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardResponse addLeaderboard(LeaderboardRequest request) {
        LeaderboardEntity leaderboardEntity = LeaderboardEntity.builder()
                .interestGroupId(request.getInterestGroupId())
                .processRatio(request.getProcessRatio())
                .ranking(request.getRanking())
                .createdAt(LocalDate.now())
                .userIdentity(request.getUserIdentity())
                .build();
        leaderboardRepository.save(leaderboardEntity);
        return LeaderboardResponse.builder()
                .interestGroupId(leaderboardEntity.getInterestGroupId())
                .processRatio(leaderboardEntity.getProcessRatio())
                .ranking(leaderboardEntity.getRanking())
                .userIdentity(leaderboardEntity.getUserIdentity())
                .build();
    }

    public LeaderboardEntity getLeaderboard(String userIdentity) {
        return leaderboardRepository.findByUserIdentity(userIdentity);
    }
}
