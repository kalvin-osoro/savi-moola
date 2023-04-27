package com.alternaonboarding.app.service.impl;

import com.alternaonboarding.app.models.Challenge;
import com.alternaonboarding.app.models.Transaction;
import com.alternaonboarding.app.models.User;
import com.alternaonboarding.app.repository.ChallengeRepository;
import com.alternaonboarding.app.repository.TransactionRepository;
import com.alternaonboarding.app.repository.UserRepository;
import com.alternaonboarding.app.service.SavingsPlanService;
import com.alternaonboarding.app.service.SavingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SavingsServiceImpl implements SavingsService {

private final TransactionRepository transactionRepository;
private final ChallengeRepository challengeRepository;
private final UserRepository userRepository;
private final SavingsPlanService savingsPlanService;


    @Override
    public Challenge subscribe(User user, int numWeeks, double initialAmount, double incrementAmount) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusWeeks(numWeeks);

        double amountToSave = initialAmount;
        double totalAmountForChallenge = amountToSave;
        double amountDepositedByUser =0.0;
        double currentAmount = 0.0;

        Challenge challenge = new Challenge(user, 1, amountToSave, totalAmountForChallenge, amountDepositedByUser, currentAmount, startDate, endDate);

        challengeRepository.save(challenge);

        //create transaction fot the initial deposit and increment amount each week
        for (int i = 0; i< numWeeks; i++) {
            double amount = amountToSave;
            if (i> 0) {
                amount += incrementAmount;
            }
            Transaction transaction = new Transaction(user, amount, LocalDateTime.now(), challenge);
            transactionRepository.save(transaction);
        }
        return challenge;
    }
    @Override
    public void makeTransaction(Integer userId, Integer challengeId, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + userId));
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid challenge id: " + challengeId));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setChallenge(challenge);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        //update the challenge progress
        double currentAmount = challenge.getCurrentAmount();
        currentAmount += amount;
        challenge.setCurrentAmount(currentAmount);

        double amountDepositedByUser = challenge.getAmountDepositedByUser();
        amountDepositedByUser += amount;
        challenge.setAmountDepositedByUser(amountDepositedByUser);
        challengeRepository.save(challenge);


    }


//    @Override
//    public void makeTransaction(User user, Challenge challenge, double amount) {
//        Transaction transaction = new Transaction();
//        transaction.setUser(user);
//        transaction.setChallenge(challenge);
//        transaction.setAmount(amount);
//        transaction.setDate(LocalDateTime.now());
//        transactionRepository.save(transaction);
//
//        //update the challenge progress
//        double currentAmount = challenge.getCurrentAmount();
//        currentAmount += amount;
//        challenge.setCurrentAmount(currentAmount);
//        challengeRepository.save(challenge);
//
//    }
}
