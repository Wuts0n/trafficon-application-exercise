package com.example.parking.repository;

import com.example.parking.model.ParkingLotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface ParkingLotRepository extends JpaRepository<ParkingLotModel, Long> {

    @Query("SELECT SUM(p.freiePlaetzeAbsolut) FROM ParkingLotModel p")
    Long findSum();

    @Query("SELECT SUM(p.freiePlaetzeAbsolut) FROM ParkingLotModel p WHERE p.id IN :ids")
    Long findSumByIds(@Param("ids") List<Long> idsParam);
}
