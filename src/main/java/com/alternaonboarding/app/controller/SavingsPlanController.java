package com.alternaonboarding.app.controller;

import com.alternaonboarding.app.dto.challenge.SavingsWeek;
import com.alternaonboarding.app.models.Challenges;
import com.alternaonboarding.app.models.User;
import com.alternaonboarding.app.repository.ChallengesRepository;
import com.alternaonboarding.app.repository.UserRepository;
import com.alternaonboarding.app.service.SavingsPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/savings-plan")
@RequiredArgsConstructor
public class SavingsPlanController {
    private final SavingsPlanService savingsPlanService;
    private final UserRepository userRepository;
    private final ChallengesRepository challengesRepository;

    @GetMapping
    public List<SavingsWeek> getSavingsPlan() {
        return savingsPlanService.generateSavingsPlan();
    }

    @PostMapping("/subscribe")
    public Challenges subscribe (@RequestParam Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return savingsPlanService.subscribe(user);
    }

    @PostMapping("/{challengeId}/progress")
    public Challenges updateProgress(@PathVariable Long challengeId, @RequestParam int amountSaved) {
        Challenges challenges = challengesRepository.findById(challengeId).orElseThrow(() -> new IllegalArgumentException("Challenge not found"));
        return savingsPlanService.updateProgress(challenges, amountSaved);
    }
    @GetMapping("/{userId}/challenges")
    public List<Challenges> getChallengesForUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return savingsPlanService.getChallengesForUser(user);
    }

}
