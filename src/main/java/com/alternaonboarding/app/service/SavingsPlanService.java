package com.alternaonboarding.app.service;

import com.alternaonboarding.app.dto.challenge.SavingsWeek;
import com.alternaonboarding.app.models.Challenges;
import com.alternaonboarding.app.models.User;

import java.util.List;

public interface SavingsPlanService {
    public List<SavingsWeek> generateSavingsPlan();

    Challenges subscribe(User user);

    Challenges updateProgress(Challenges challenges, int amountSaved);
    List<Challenges> getChallengesForUser(User user);
}
