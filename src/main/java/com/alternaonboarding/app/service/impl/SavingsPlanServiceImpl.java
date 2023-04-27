package com.alternaonboarding.app.service.impl;

//import com.alternaonboarding.app.models.Challenge;
import com.alternaonboarding.app.models.Challenge;
import com.alternaonboarding.app.models.Transaction;
import com.alternaonboarding.app.models.User;
import com.alternaonboarding.app.repository.ChallengeRepository;
import com.alternaonboarding.app.repository.TransactionRepository;
import com.alternaonboarding.app.repository.UserRepository;
import com.alternaonboarding.app.service.SavingsPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavingsPlanServiceImpl implements SavingsPlanService {


    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
//    @Override
//    public List<SavingsWeek> generateSavingsPlan() {
//        List<SavingsWeek> savingsPlan = new ArrayList<>();
//
//        int currentAmountToSave = 50;
//        int totalAmount = 0;
//
//        //get the start date of the challenge
////        LocalDate startDate = LocalDate.now();
//
//        for (int i= 1; i<=52; i++) {
//
//            Challenge challenge = new Challenge();
////            SavingsWeek week = new SavingsWeek();
//            challenge.setWeekNumber(i);
//            challenge.setAmountToSave(currentAmountToSave);
//            totalAmount =totalAmount + currentAmountToSave;
//            challenge.setTotalAmount(totalAmount);
////            savingsPlan.add(challenge);
//
//            //calculate the end date of the challenge
////            LocalDate endDate = startDate.plusWeeks(52);
////
////            //set the start and end dates of the challenge
////            challenge.setStartDate(startDate);
////            challenge.setEndDate(endDate);
//
//            Challenge saveChallenge = challengeRepository.save(challenge);
//
//
////            totalAmount =totalAmount + currentAmountToSave;
//            currentAmountToSave +=50;
////            totalAmount =totalAmount + currentAmountToSave;
//            System.out.println("Total amount save is: " + totalAmount);
//
//        }
//
//        return savingsPlan;
//    }

//    @Override
//    public List<Challenge> generateSavingsPlan(LocalDate startDate, int numWeeks, int initialAmount, int incrementAmount) {
//        List<Challenge> savingsPlan = new ArrayList<>();
//
//        int totalAmount = 0;
//
//        for (int i=1; i<=numWeeks;  i++) {
//            Challenge challenge = new Challenge();
//            challenge.setWeekNumber(i);
//            challenge.setAmountToSave(initialAmount + (i - 1) * incrementAmount);
//            totalAmount += challenge.getAmountToSave();
//            challenge.setTotalAmountSaved(totalAmount);
//            challenge.setStartDate(startDate);
//            challenge.setEndDate(startDate.plusWeeks(numWeeks));
//
//            savingsPlan.add(challenge);
//        }
//        return savingsPlan;
//    }

//    @Override
//    public Challenge subscribe(User user, int numWeeks, double initialAmount, double incrementAmount) {
//        LocalDate startDate = LocalDate.now();
////        LocalDate endDate = startDate.plusWeeks(52);
//        LocalDate endDate = startDate.plusWeeks(numWeeks);
////        LocalDate endWeekDate = startDate.plusWeeks(1);
////        Challenge challenge = new Challenge(user, 1, initialAmount, 0, startDate, endDate);
//        Challenge challenge = new Challenge(user, 1, initialAmount, 0, startDate, endDate);
//
//        challengeRepository.save(challenge);
//
//        return challenge;
//    }

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

//    @Override
//    public void checkForProgress (Challenge challenge) {
//        User user = challenge.getUser();
//        LocalDate startDate = challenge.getStartDate();
//        LocalDate currentDate = LocalDate.now();
//
//        if (currentDate.isBefore(startDate)) {
//            throw new IllegalStateException("Challenge has not started yet");
//        }
//
//        int numWeeks = challenge.getNumWeeks();
//        int initialAmount = challenge.getInitialAmount();
//        int incrementAmount = challenge.getIncrementAmount();
//        int totalAmount = initialAmount;
//
//        for (int i = 1; i<=numWeeks; i++) {
//            if (currentDate.isBefore(startDate.plusWeeks(i))) {
//                break;
//            }
//            totalAmount += incrementAmount;
//        }
//
//        user.setSavings(totalAmount);
//        userRepository.save(user);
//
//    }

//    public void checkProgress(User user) {
//        LocalDate startDate = LocalDate.now();
//        LocalDate endDate = startDate.plusWeeks(52);
//
//        Optional<Challenge> challengeOptional = challengeRepository.findByUserAndStartDate(user, startDate);
//        if (challengeOptional.isPresent()) {
//            Challenge challenge = challengeOptional.get();
//
//            LocalDate currentDate = LocalDate.now();
//            if (currentDate.isAfter(endDate)) {
//                System.out.println("Challenge has ended.");
//                return;
//            }
//
//            int weekNumber = (int) ChronoUnit.WEEKS.between(startDate, currentDate) + 1;
//
//            int expectedAmount = challenge.getInitialAmount() + ((weekNumber - 1) * challenge.getIncrementAmount());
//            int actualAmount = challenge.getSavedAmount();
//
//            if (actualAmount < expectedAmount) {
//                int remainingAmount = expectedAmount - actualAmount;
//                System.out.println("You're behind on your savings goal. You still need to save $" + remainingAmount + " to stay on track.");
//            } else if (actualAmount == expectedAmount) {
//                System.out.println("You've reached your savings goal for this week. Keep up the good work!");
//            } else {
//                int excessAmount = actualAmount - expectedAmount;
//                System.out.println("Congratulations, you've exceeded your savings goal for this week. You've saved an extra $" + excessAmount + "!");
//            }
//        } else {
//            System.out.println("User has not subscribed to the challenge.");
//        }
//    }
//
//
//
//
//
//    @Override
//    public int calculateTotalAmountSaved(User user, Challenge challenge)
//     {
//        List<Transaction> transactions = transactionRepository.findByUserAndChallenge(user, challenge);
//        int totalAmountSaved = 0;
//
//        for (Transaction transaction: transactions) {
//            totalAmountSaved += transaction.getAmount();
//        }
//        return totalAmountSaved;
//    }
//
//    public String provideFeedback (User user, Challenge challenge) {
//        int totalAmountExpected = challenge.getAmountToSave();
//        int totalAmountSaved = calculateTotalAmountSaved(user, challenge);
//
//        if (totalAmountSaved < totalAmountExpected) {
//            int amountLeftToSave = totalAmountExpected - totalAmountSaved;
//            System.out.println("you still need to save " + amountLeftToSave + "to complete the challenge");
//
//            String message = "you still need to save " + amountLeftToSave + "to complete the challenge";
//
//            return  message;
//
//        }
//        else if (totalAmountSaved == totalAmountExpected) {
//            System.out.println("Congratulations, you have completed the challenge!");
//
//            String message = "Congratulations, you have completed the challenge!";
//
//            return message;
//
//        } else {
//            int amountExtraSaved = totalAmountSaved - totalAmountExpected;
//            System.out.println("You have saved " + amountExtraSaved + "more that the challenge requires.");
//            String message = "You have saved " + amountExtraSaved + "more that the challenge requires.";
//
//            return message;
//        }
//
//    }
//
//
////    @Override
////    public Challenges subscribe(User user) {
////        LocalDate startDate = LocalDate.now();
////        int currentAmountToSave = 50;
////        int totalAmountSaved = 0;
////        Challenges challenges = new Challenges(user, 1, currentAmountToSave, totalAmountSaved, startDate, null );
////        challengesRepository.save(challenges);
////        return challenges;
////    }
//
//    @Override
//    public Challenge updateProgress(Challenge challenge, int amountSaved) {
//        challenge.setTotalAmountSaved(challenge.getTotalAmountSaved() + amountSaved);
//        challenge.setAmountToSave(challenge.getAmountToSave() + 50);
//        if (challenge.getWeekNumber() == 52) {
//            challenge.setEndDate(LocalDate.now());
//        }
//        return challengeRepository.save(challenge);
//    }
//
//    @Override
//    public List<Challenge> getChallengesForUser(User user) {
//        return challengeRepository.findByUser(user);
//    }


}
