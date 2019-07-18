package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {
    int deleteAllById(int id);
    List<ParkingLot> findAllByName(String name);
    ParkingLot findById(int id);
}
