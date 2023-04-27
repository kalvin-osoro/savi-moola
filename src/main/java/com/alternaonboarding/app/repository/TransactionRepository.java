package com.alternaonboarding.app.repository;

import com.alternaonboarding.app.models.Challenge;
import com.alternaonboarding.app.models.Transaction;
import com.alternaonboarding.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserAndChallenge(User user, Challenge challenge);
}
