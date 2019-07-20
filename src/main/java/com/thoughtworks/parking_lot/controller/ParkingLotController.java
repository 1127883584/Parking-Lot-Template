package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @PostMapping("/parking-lots")
    public ResponseEntity createParkingLots(@RequestBody ParkingLot parkingLot) {
        try{
            ParkingLot newParkingLot = parkingLotRepository.saveAndFlush(parkingLot);
            return ResponseEntity.ok(newParkingLot);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/parking-lots/{id}")
    public ResponseEntity deleteParkingLots(@PathVariable int id){
        int statusCode = parkingLotRepository.deleteAllById(id);
        return ResponseEntity.ok(statusCode);
    }

    @GetMapping("/parking-lots")
    public ResponseEntity getParkingLots(
            @RequestParam(defaultValue = "all") String parkingLotName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int pageSize){
        if (!parkingLotName.equals("all")) {
            return ResponseEntity.ok(parkingLotRepository.findAllByName(parkingLotName));
        }
        if (page > 0 && pageSize > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            Page<ParkingLot> parkingLots =  parkingLotRepository.findAll(pageable);
            return ResponseEntity.ok(parkingLots.getContent());
        }
        return ResponseEntity.ok(parkingLotRepository.findAll());
    }

    @GetMapping("/parking-lots/{id}")
    public ResponseEntity getParkingLotById(@PathVariable int id){
        return ResponseEntity.ok(parkingLotRepository.findById(id));
    }

    @PutMapping("/parking-lots/{id}")
    public ResponseEntity updateParkingLot(@PathVariable int id, @RequestBody ParkingLot parkingLot){
        parkingLot.setId(id);
        ParkingLot updateParkingLot = parkingLotRepository.saveAndFlush(parkingLot);
        return ResponseEntity.ok(updateParkingLot);
    }
}