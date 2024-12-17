package com.example.parking.controller;

import com.example.parking.model.ParkingLotModel;
import com.example.parking.model.WrappedLongValue;
import com.example.parking.model.parkingLotWfsDTO.ParkingLotWfsDTO;
import com.example.parking.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/parkingLots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    // get all parking lots
    @GetMapping
    public List<ParkingLotModel> findAll() {
        return parkingLotService.findAll();
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

    // get a parking lot by ID
    @GetMapping("/{id}")
    public Optional<ParkingLotModel> findById(@PathVariable Long id) {
        return parkingLotService.findById(id);
    }

    // delete a parking lot
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        parkingLotService.deleteById(id);
    }

    // get the sum of all empty parking spaces
    @GetMapping("/sum")
    public ResponseEntity<WrappedLongValue> findSumByIds(@RequestParam(value = "ids", required = false) String idsParam) {
        return ResponseEntity.ok(parkingLotService.findSumByIds(idsParam));
    }

    // import parking lots by WFS Json
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PutMapping("/wfs")
    public List<ParkingLotModel> importWfsJson(@RequestBody ParkingLotWfsDTO parkingLotWfsDTO) {
        return parkingLotService.save(parkingLotWfsDTO);
    }
}
