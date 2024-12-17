package com.example.parking.controller;

import com.example.exception.BadRequestException;
import com.example.parking.model.ParkingLotModel;
import com.example.parking.model.SumResponse;
import com.example.parking.model.parkingLotWfsDTO.ParkingLotWfsDTO;
import com.example.parking.service.ParkingLotService;
import com.example.parking.util.Parser;
import jakarta.persistence.EntityNotFoundException;
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
    public ParkingLotModel findById(@PathVariable Long id) {
        Optional<ParkingLotModel> entity = parkingLotService.findById(id);
        return entity.orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " was not found."));
    }

    // delete a parking lot
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        parkingLotService.deleteById(id);
    }

    // get the sum of all empty parking spaces
    @GetMapping("/sum")
    public ResponseEntity<SumResponse> findSumByIds(@RequestParam(value = "ids", required = false) String idsParam) {
        Long sum;
        List<Long> ids = new ArrayList<>();
        if (idsParam != null && !idsParam.isEmpty()) {
            try {
                ids = Parser.stringToLongList(idsParam);
            } catch (NumberFormatException ex) {
                throw new BadRequestException("Some ids are not valid numbers, or you are not using \",\" as delimiter: " + ex.getMessage(), ex);
            }
        }
        sum = parkingLotService.findSumByIds(ids);
        return ResponseEntity.ok(new SumResponse(sum));
    }

    // import parking lots by WFS Json
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PutMapping("/wfs")
    public List<ParkingLotModel> importWfsJson(@RequestBody ParkingLotWfsDTO parkingLotWfsDTO) {
        return parkingLotService.save(parkingLotWfsDTO);
    }
}
