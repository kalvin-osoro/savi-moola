package com.alternaonboarding.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "phoneNumber"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;


    private String fullName;


    private int idNumber;


    @Column(name = "date_of_birth", columnDefinition = "DATE DEFAULT '1970-01-01'")
    private Date dateOfBirth;



//    @Column(name = "date_of_birth")
//    private Date dob;


    private String gender;


    private String phoneNumber;


    private String email;

    private String pin = String.valueOf(0000);

    private boolean verified = false;

   @ToString.Exclude // Exclude challenges from toString to avoid lazy initialization errors

   @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Challenge> challenges;


    public User(int id) {
        this.id = id;
    }

   public User(String fullName, int idNumber, Date dateOfBirth, String gender, String phoneNumber, String email, String pin ) {
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pin = pin;

    }


}
