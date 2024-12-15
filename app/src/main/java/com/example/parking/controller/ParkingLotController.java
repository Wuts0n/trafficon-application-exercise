package com.example.parking.controller;

import com.example.exception.UnprocessableEntityException;
import com.example.parking.model.ParkingLotModel;
import com.example.parking.model.WrappedLongValue;
import com.example.parking.service.ParkingLotService;
import com.example.parking.utility.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/parkingLots")
public class ParkingLotController {

    private static final String template = "Hello, %s!";
    private static final String ALL = "*";


    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping
    public List<ParkingLotModel> findAll() {
        return parkingLotService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ParkingLotModel> findById(@PathVariable Long id) {
        return parkingLotService.findById(id);
    }

    @GetMapping("/sum")
    public ResponseEntity<WrappedLongValue> findSumByIds(@RequestParam(value = "ids", required = false) String idsParam) {
        return ResponseEntity.ok(parkingLotService.findSumByIds(idsParam));
    }

    // create a parking lot
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public ParkingLotModel create(@RequestBody ParkingLotModel parkingLot) {
        return parkingLotService.save(parkingLot);
    }

    // update a parking lot
    @PutMapping
    public ParkingLotModel update(@RequestBody ParkingLotModel parkingLot) {
        return parkingLotService.save(parkingLot);
    }

    // delete a parking lot
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        parkingLotService.deleteById(id);
    }

    @GetMapping("/parking")
    public String getParkingLotById(@RequestParam(value = "ids", defaultValue = ALL) String idsParam) {
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

    @PostMapping("/parking")
    public String postParkingLot(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }

}
