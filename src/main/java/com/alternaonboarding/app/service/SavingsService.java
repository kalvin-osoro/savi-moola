package com.alternaonboarding.app.service;

import com.alternaonboarding.app.models.Challenge;
import com.alternaonboarding.app.models.User;

public interface SavingsService {
//    void startSaving(User user, int amountSaved);

//    void makeTransaction(User user, Challenge challenge, double amount);
       void makeTransaction(Integer userId, Integer challengeId, double amount);

       Challenge subscribe(User user, int numWeeks, double initialAmount, double incrementAmount);
}
