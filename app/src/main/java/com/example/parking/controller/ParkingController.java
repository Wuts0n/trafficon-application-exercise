package com.example.parking.controller;

import com.example.exception.UnprocessableEntityException;
import com.example.parking.utility.Parser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class ParkingController {

    private static final String template = "Hello, %s!";
    private static final String ALL = "all";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/parking")
    public String getParkingById(@RequestParam(value = "ids", defaultValue = ALL) String idsParam) {
        List<Long> ids = new ArrayList<>();
        if (idsParam.equals(ALL)) {
            //TODO get all ids
            //ids.add(getAllIds());
            ids.add(5453L);
            ids.add(42L);
        } else {
            try {
                ids = Parser.stringToLongList(idsParam);
            } catch (NumberFormatException ex) {
                throw new UnprocessableEntityException("Some ids are not valid numbers, or you are not using \",\" as delimiter: " + ex.getMessage(), ex);
            }
        }
        //TODO return proper data
        return ids.toString();
    }

    @PutMapping("/parking")
    public String putParking(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }

}
