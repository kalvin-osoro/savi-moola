package com.alternaonboarding.app.service;

import com.alternaonboarding.app.models.Challenge;
import com.alternaonboarding.app.models.User;

import java.time.LocalDate;
import java.util.List;

public interface SavingsPlanService {

//    public List<SavingsWeek> generateSavingsPlan();
//    List<Challenge> generateSavingsPlan(LocalDate startDate, int numWeeks, int initialAmount, int incrementAmount);

//    Challenges subscribe(User user);
//Challenge subscribe(User user, int numWeeks, int initialAmount, int incrementAmount);
    Challenge subscribe(User user, int numWeeks, double initialAmount, double incrementAmount);

//    int calculateTotalAmountSaved(User user, Challenges challenge);
//    int calculateTotalAmountSaved(User user, Challenge challenge);
//    Challenge updateProgress(Challenge challenge, int amountSaved);
//    List<Challenge> getChallengesForUser(User user);

//    String provideFeedback(User user, Challenge challenge);


//    void checkForProgress (Challenge challenge);
//    void checkProgress(User user);
}
