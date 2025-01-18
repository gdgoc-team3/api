package com.team3.gdgoc.interest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<InterestEntity, Long> {
    InterestEntity findByUserId(Long userId);
}
