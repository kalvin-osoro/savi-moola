package com.alternaonboarding.app.service.impl;

import com.alternaonboarding.app.dto.challenge.SavingsWeek;
import com.alternaonboarding.app.models.Challenge;
import com.alternaonboarding.app.models.Challenges;
import com.alternaonboarding.app.models.User;
import com.alternaonboarding.app.repository.ChallengeRepository;
import com.alternaonboarding.app.repository.ChallengesRepository;
import com.alternaonboarding.app.repository.UserRepository;
import com.alternaonboarding.app.service.SavingsPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SavingsPlanServiceImpl implements SavingsPlanService {

    private final ChallengeRepository challengeRepository;
    private final ChallengesRepository challengesRepository;
    private final UserRepository userRepository;
    @Override
    public List<SavingsWeek> generateSavingsPlan() {
        List<SavingsWeek> savingsPlan = new ArrayList<>();

        int currentAmountToSave = 50;
        int totalAmount = 0;

        //get the start date of the challenge
//        LocalDate startDate = LocalDate.now();

        for (int i= 1; i<=52; i++) {

            Challenge challenge = new Challenge();
//            SavingsWeek week = new SavingsWeek();
            challenge.setWeekNumber(i);
            challenge.setAmountToSave(currentAmountToSave);
            totalAmount =totalAmount + currentAmountToSave;
            challenge.setTotalAmount(totalAmount);
//            savingsPlan.add(challenge);

            //calculate the end date of the challenge
//            LocalDate endDate = startDate.plusWeeks(52);
//
//            //set the start and end dates of the challenge
//            challenge.setStartDate(startDate);
//            challenge.setEndDate(endDate);

            Challenge saveChallenge = challengeRepository.save(challenge);


//            totalAmount =totalAmount + currentAmountToSave;
            currentAmountToSave +=50;
//            totalAmount =totalAmount + currentAmountToSave;
            System.out.println("Total amount save is: " + totalAmount);

        }

        return savingsPlan;
    }

    @Override
    public Challenges subscribe(User user) {
        LocalDate startDate = LocalDate.now();
        int currentAmountToSave = 50;
        int totalAmountSaved = 0;
        Challenges challenges = new Challenges(user, 1, currentAmountToSave, totalAmountSaved, startDate, null );
        challengesRepository.save(challenges);
        return challenges;
    }

    @Override
    public Challenges updateProgress(Challenges challenges, int amountSaved) {
        challenges.setTotalAmountSaved(challenges.getTotalAmountSaved() + amountSaved);
        challenges.setAmountToSave(challenges.getAmountToSave() + 50);
        if (challenges.getWeekNumber() == 52) {
            challenges.setEndDate(LocalDate.now());
        }
        return challengesRepository.save(challenges);
    }

    @Override
    public List<Challenges> getChallengesForUser(User user) {
        return challengesRepository.findByUser(user);
    }


}
