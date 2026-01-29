package com.grupp5.sf2api.models.theater;

import com.grupp5.sf2api.repositories.theater.TheaterRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class PopulateTheater implements CommandLineRunner {
    private TheaterRepository theaterRepository;

    @Override
    public void run(String... args) {
        if (!theaterRepository.findAll().isEmpty()) return;

        List<Theater> theaters = new ArrayList<>();

        Theater t1 = new Theater("Salong 1", 316, 15, 22);
        Theater t2 = new Theater("Salong 2", 222, 11, 21);
        Theater t3 = new Theater("Salong 3", 77, 7, 11);

        theaters.add(t1);
        theaters.add(t2);
        theaters.add(t3);

        theaterRepository.saveAll(theaters);
    }
}