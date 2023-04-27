package com.alternaonboarding.app.repository;

import com.alternaonboarding.app.models.Challenge;
import com.alternaonboarding.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByUser(User user);

    Optional<Challenge> findByUserAndStartDate(User user, LocalDate startDate);

    Optional<Challenge> findById(Integer id);
}
