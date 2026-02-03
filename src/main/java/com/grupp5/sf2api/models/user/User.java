package com.grupp5.sf2api.models.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.grupp5.sf2api.models.tickets.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private AccountType accountType;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private List<Ticket> tickets;

    // Constructors
    public User(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.accountType = AccountType.USER;
        this.createdAt = LocalDateTime.now();
    }
}

