package com.alternaonboarding.app.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity

@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private double amount;

    private LocalDateTime date;


    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;


    public Transaction(User user, double amount, LocalDateTime now, Challenge challenge) {
        this.user = user;
        this.amount = amount;
        this.date = now;
        this.challenge = challenge;
    }

    public Integer getUserId() {
      return this.user.getId();
    }
    public Integer getChallengeId() {
        return this.challenge.getId();
    }
}
