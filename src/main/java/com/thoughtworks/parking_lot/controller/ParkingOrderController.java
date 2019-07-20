package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingOrderController {
    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @PostMapping("/parking-orders")
    public ResponseEntity createParkingOrders(@RequestBody ParkingOrder parkingOrder) {
        try{
            ParkingOrder newParkingOrder = parkingOrderRepository.saveAndFlush(parkingOrder);
            return ResponseEntity.ok(newParkingOrder);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
