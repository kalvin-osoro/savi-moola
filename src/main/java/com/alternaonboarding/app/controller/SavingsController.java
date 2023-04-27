package com.alternaonboarding.app.controller;

import com.alternaonboarding.app.models.Challenge;
import com.alternaonboarding.app.models.Transaction;
import com.alternaonboarding.app.models.User;
import com.alternaonboarding.app.repository.ChallengeRepository;
import com.alternaonboarding.app.repository.UserRepository;
import com.alternaonboarding.app.service.SavingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savings")
@RequiredArgsConstructor
public class SavingsController {


    private final SavingsService savingsService;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

//    @PostMapping("/transactions")
//    public ResponseEntity<String> makeTransaction (@RequestBody Transaction transaction) {
//        User user = transaction.getUser();
//        Challenge challenge = transaction.getChallenge();
//        double amount = transaction.getAmount();
//
//        try {
//            savingsService.makeTransaction(user, challenge, amount);
//            return new ResponseEntity<>("Transaction successful.", HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error making transaction: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//        }
//    }

    @PostMapping("/transactions")
    public ResponseEntity<String> makeTransaction (@RequestBody Transaction transaction) {
        Integer userId = transaction.getUserId();
        Integer challengeId = transaction.getChallengeId();
        double amount = transaction.getAmount();

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
            Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new IllegalArgumentException("Invalid challenge id"));

            savingsService.makeTransaction(user.getId(), challenge.getId(), amount);
//            savingsService.makeTransaction(user, challenge, amount);
        } catch (Exception e) {

            return new ResponseEntity<>("Error making transaction: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

}
