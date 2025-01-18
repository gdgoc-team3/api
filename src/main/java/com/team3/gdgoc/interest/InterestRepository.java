package com.team3.gdgoc.interest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<InterestEntity, Long> {
    InterestEntity findByUserId(Long userId);

    List<InterestEntity> findAllByUserId(Long userId);

}
