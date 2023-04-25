package com.alternaonboarding.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Challenges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int weekNumber;

    private int amountToSave;

    private int totalAmountSaved;

    private LocalDate startDate;

    private LocalDate endDate;

    public Challenges(User user, int i, int currentAmountToSave, int totalAmountSaved, LocalDate startDate, Object o) {
    }
}
