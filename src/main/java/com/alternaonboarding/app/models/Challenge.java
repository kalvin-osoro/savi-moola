package com.alternaonboarding.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Table(name = "challenges")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private int weekNumber;

    private double amountToSave;
    private double totalAmountForChallenge;

    private double amountDepositedByUser;
    private double currentAmount;

    private LocalDate startDate;

    private LocalDate endDate;

//    public Challenge(User user, int i, int currentAmountToSave, int totalAmountSaved, LocalDate startDate, Object o) {
//
//    }

    public Challenge(int id) {
        this.id = id;
    }

    public Challenge(User user, int weekNumber, double amountToSave, double totalAmountForChallenge, double amountDepositedByUser, double currentAmount, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.weekNumber = weekNumber;
        this.amountToSave = amountToSave;
        this.totalAmountForChallenge = totalAmountForChallenge;
        this.amountDepositedByUser = amountDepositedByUser;
        this.currentAmount = currentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
