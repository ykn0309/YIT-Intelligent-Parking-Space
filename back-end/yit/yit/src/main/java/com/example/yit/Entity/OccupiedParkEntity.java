package com.example.yit.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "occupiedparks")
public class OccupiedParkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer parkId;

    @Column(unique = true)
    private Integer userId;
    private String carId;
    private String startDate;
    private Integer occupied;

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCarId() {
        return carId;
    }
    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public Integer getOccupied() {
        return occupied;
    }
    public void setOccupied(Integer occupied) {
        this.occupied = occupied;
    }
}
