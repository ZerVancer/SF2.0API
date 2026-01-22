package com.grupp5.sf2api.models.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "movies")
@Getter
@Setter
@AllArgsConstructor
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID movieId;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private int duration;

  @Column
  private String description;

  // Constructor
  protected Movie() {}

  public Movie(String title, int duration)  {
    this.title = title;
    this.duration = duration;
  }

  public Movie(String title, int duration, String description) {
    this(title, duration);
    this.description = description;
  }
}
