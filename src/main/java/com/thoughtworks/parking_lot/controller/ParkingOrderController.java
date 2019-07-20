package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ParkingOrderController {
    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private ParkingOrderService parkingOrderService;

    @PostMapping("/parking-orders")
    public ResponseEntity createParkingOrders(@RequestBody ParkingOrder parkingOrder) {
        ParkingOrder newParkingOrder = parkingOrderService.createParkingOrder(parkingOrder);
        return ResponseEntity.ok(newParkingOrder);
    }

    @PutMapping("/parking-orders/{id}")
    public ResponseEntity createParkingOrders(@PathVariable int id) {
        ParkingOrder updatedParkingOrder = parkingOrderService.closeParkingOrder(id);
        return ResponseEntity.ok(updatedParkingOrder);
    }
}
