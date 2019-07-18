package com.thoughtworks.parking_lot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(value = 0)
    private int capacity;

    @Column(nullable = false)
    private String position;

    public ParkingLot(String name, @Min(value = 0) int capacity, String position) {
        this.name = name;
        this.capacity = capacity;
        this.position = position;
    }

    public ParkingLot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
