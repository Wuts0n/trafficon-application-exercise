package com.example.parking.service;

import com.example.parking.model.ParkingLotModel;
import com.example.parking.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public List<ParkingLotModel> findAll() {
        return parkingLotRepository.findAll();
    }

    public Optional<ParkingLotModel> findById(Long id) {
        return parkingLotRepository.findById(id);
    }

    public ParkingLotModel save(ParkingLotModel parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    public void deleteById(Long id) {
        parkingLotRepository.deleteById(id);
    }
}
