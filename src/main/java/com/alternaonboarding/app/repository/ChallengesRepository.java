package com.alternaonboarding.app.repository;

import com.alternaonboarding.app.models.Challenges;
import com.alternaonboarding.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengesRepository extends JpaRepository<Challenges, Long> {
    List<Challenges> findByUser(User user);
}
