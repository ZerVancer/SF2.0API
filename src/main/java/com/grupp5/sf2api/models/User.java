package com.grupp5.sf2api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/*
* User behöver kopplingen till Tickets för att fungera.
* */
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;
    private String sessionToken;


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Ticket> tickets;


    // Constructors
    protected User() {}

    public User(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }
}

