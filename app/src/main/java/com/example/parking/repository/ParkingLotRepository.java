package com.example.parking.repository;

import com.example.parking.model.ParkingLotModel;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface ParkingLotRepository extends JpaRepository<ParkingLotModel, Long> {

}
