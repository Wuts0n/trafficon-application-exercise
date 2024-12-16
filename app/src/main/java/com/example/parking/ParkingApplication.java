package com.example.parking;

import com.example.parking.model.ParkingLotModel;
import com.example.parking.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.geo.Point;

import java.util.List;

@SpringBootApplication
public class ParkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }


    @Autowired
    ParkingLotRepository parkingLotRepository;

    // Run this if app.db.init.enabled = true
    @Bean
    @ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
    public CommandLineRunner demoCommandLineRunnerParkingLots() {
        return _ -> {
            System.out.println("Inserting dummy parking lot values…");

            ParkingLotModel lot1 = new ParkingLotModel();
            lot1.setAdresse("at home");
            lot1.setFax("54667/3465");
            lot1.setBezeichnung("Parkplatz Immervoll");
            lot1.setId(5487L);
            lot1.setFreiePlaetzeAbsolut(574855);
            lot1.setFreiePlaetzeProzent(15D);
            lot1.setFid("parkplatz.5487");
            lot1.setPoint(new Point(13.03783547D, 47.79744591D));

            ParkingLotModel lot2 = new ParkingLotModel();
            lot2.setAdresse("Salzbuaich");
            lot2.setFax("54667/34633333333335");
            lot2.setBezeichnung("Parkplatz Fisch");
            lot2.setId(55555L);
            lot2.setFreiePlaetzeAbsolut(5);
            lot2.setFreiePlaetzeProzent(80.9511D);
            lot2.setFid("parkplatz.55555");
            lot2.setPoint(new Point(13.04368224D, 47.81539145D));

            parkingLotRepository.saveAll(List.of(lot1, lot2));

            System.out.println("Done entering dummy parking lot values.");
        };
    }

}
