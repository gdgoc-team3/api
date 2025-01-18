package com.team3.gdgoc.leaderboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderboardRepository extends JpaRepository<LeaderboardEntity, Long>{

    LeaderboardEntity findByUserIdentity(String userIdentity);
}
