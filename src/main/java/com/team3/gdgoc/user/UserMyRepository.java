package com.team3.gdgoc.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMyRepository extends JpaRepository<UserEntity, Long> {

}
