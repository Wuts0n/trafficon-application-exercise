package com.example.parking.service;

import com.example.exception.UnprocessableEntityException;
import com.example.parking.model.WrappedLongValue;
import com.example.parking.model.ParkingLotModel;
import com.example.parking.repository.ParkingLotRepository;
import com.example.parking.utility.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public WrappedLongValue findSumByIds(String idsParam) {
        Long sum;
        if (idsParam == null || idsParam.isEmpty()) {
            sum = parkingLotRepository.findSum();
        } else {
            List<Long> ids;
            try {
                ids = Parser.stringToLongList(idsParam);
            } catch (NumberFormatException ex) {
                throw new UnprocessableEntityException("Some ids are not valid numbers, or you are not using \",\" as delimiter: " + ex.getMessage(), ex);
            }
            sum = parkingLotRepository.findSumByIds(ids);
        }
        return new WrappedLongValue(sum);
    }

    public ParkingLotModel save(ParkingLotModel parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    public void deleteById(Long id) {
        parkingLotRepository.deleteById(id);
    }
}
