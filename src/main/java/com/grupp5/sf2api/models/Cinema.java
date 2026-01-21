package com.grupp5.sf2api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/*
 * Cinema behöver koppling till Theaters för att fungera.
 * */
@Entity
@Table(name = "cinemas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "cinemas",
            cascade = CascadeType.ALL)
    private List<Theater> theaters;

    //Constructors
    public Cinema(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
