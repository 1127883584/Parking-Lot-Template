package com.thoughtworks.parking_lot.model;

import javax.persistence.*;

public class ParkingOrder {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company")
    private ParkingLot parkingLot;

    @Column(nullable = false)
    private String carNumber;

    @Column(nullable = false)
    private long startTime;

    @Column(nullable = false)
    private long endTime;

    @Column(nullable = false)
    private String status;

    public ParkingOrder(ParkingLot parkingLot, String carNumber, long startTime, long endTime, String status) {
        this.parkingLot = parkingLot;
        this.carNumber = carNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public ParkingOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
