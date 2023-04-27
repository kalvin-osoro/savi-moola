package com.alternaonboarding.app.controller;

import com.alternaonboarding.app.models.Challenge;
import com.alternaonboarding.app.models.User;
import com.alternaonboarding.app.repository.ChallengeRepository;
import com.alternaonboarding.app.repository.TransactionRepository;
import com.alternaonboarding.app.repository.UserRepository;
import com.alternaonboarding.app.service.SavingsPlanService;
import com.alternaonboarding.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/savings-plan")
@RequiredArgsConstructor
public class SavingsPlanController {
    private final SavingsPlanService savingsPlanService;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    private final TransactionRepository transactionRepository;

    private final UserService userService;

//    @GetMapping
//    public List<Challenge> getSavingsPlan() {
//        return savingsPlanService.generateSavingsPlan(LocalDate.now(), 52, 50, 50);
//    }

    @PostMapping("/subscribe")
    public ResponseEntity<Challenge>  subscribe (@RequestParam Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
       Challenge challenge = savingsPlanService.subscribe(user, 52, 50, 50);
        return ResponseEntity.ok(challenge);
    }



//    @GetMapping("/{challengeId}/transactions")
//    public ResponseEntity<List<Transaction>> getTransactionsByChallengeId(@PathVariable Long challengeId) {
//        List<Transaction> transactions = savingsPlanService.getTransactionsByChallengeId(challengeId);
//        return ResponseEntity.ok(transactions);
//    }

//    @PostMapping("/{challengeId}/transactions")
//    public ResponseEntity<Transaction> addTransaction(@PathVariable Long challengeId, @RequestBody Transaction transaction) {
//        Challenges challenge = savingsPlanService.getChallengeById(challengeId);
//        User user = userService.getUserById(transaction.getUserId());
//        transaction.setChallenge(challenge);
//        transaction.setUser(user);
//        Transaction savedTransaction = transactionService.addTransaction(transaction);
//        challengeService.provideFeedback(user, challenge);
//        return ResponseEntity.ok(savedTransaction);
//    }

    // Mapping to calculate total amount saved for a user's challenge
//     @GetMapping("/calculate-total-amount-saved")
//    public int calculateTotalAmountSavedForUserChallenge(@RequestParam Long userId, @RequestParam Long challengeId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new RuntimeException("Challenge not found"));
//        return savingsPlanService.calculateTotalAmountSaved(user, challenge);
//    }
//    // Mapping to provide feedback to a user about their challenge progress
//    @GetMapping("/provide-feedback")
//    public void provideFeedbackForUserChallenge (@RequestParam Long userId, @RequestParam Long challengeId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new RuntimeException("Challenge not found"));
//        savingsPlanService.provideFeedback(user, challenge);
//    }
//    @GetMapping("/provide-feedback")
//    public ResponseEntity<String> provideFeedbackForUserChallenge (@RequestParam Long userId, @RequestParam Long challengeId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User now found"));
//        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(()-> new RuntimeException("Challenge not found"));
//
//        //call the service to provide feedback
//
//        savingsPlanService.provideFeedback(user, challenge);
//
//        //create the response entity with a success message
//        String message = "Feedback provided successfully for User " + user.getId() + " and Challenge " + challenge.getId();
//        return ResponseEntity.ok(message);
//    }


//    @PostMapping("/{challengeId}/progress")
//    public Challenge updateProgress(@PathVariable Long challengeId, @RequestParam int amountSaved) {
//        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new IllegalArgumentException("Challenge not found"));
//        return savingsPlanService.updateProgress(challenge, amountSaved);
//    }
//    @GetMapping("/{userId}/challenges")
//    public List<Challenge> getChallengesForUser(@PathVariable Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
//        return savingsPlanService.getChallengesForUser(user);
//    }

}
