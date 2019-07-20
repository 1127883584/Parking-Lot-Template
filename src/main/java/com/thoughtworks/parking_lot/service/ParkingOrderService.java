package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParkingOrderService {
    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingOrder createParkingOrder(ParkingOrder parkingOrder){
        List<ParkingOrder> parkingOrders = parkingOrderRepository.findAllByParkingLotId(parkingOrder.getParkingLot().getId());
        ParkingLot parkingLotTemp = parkingLotRepository.findById(parkingOrder.getParkingLot().getId());
        if (parkingOrders.size() < parkingLotTemp.getCapacity()) {
            parkingOrder.setStartTime((new Date()).getTime());
            ParkingOrder newParkingOrder = parkingOrderRepository.saveAndFlush(parkingOrder);
            System.out.println(newParkingOrder);
            return newParkingOrder;
        } else {
            return null;
        }
    }

    public ParkingOrder closeParkingOrder(int parkingOrderId){
        ParkingOrder parkingOrder = parkingOrderRepository.findById(parkingOrderId).orElse(null);
        parkingOrder.setEndTime((new Date()).getTime());
        parkingOrder.setStatus("close");
        return parkingOrderRepository.save(parkingOrder);
    }
}
