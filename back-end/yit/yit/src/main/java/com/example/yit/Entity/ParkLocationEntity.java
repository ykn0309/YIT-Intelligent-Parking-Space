package com.example.yit.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parklocations")
public class ParkLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer parkId;

    @Column(unique = true)
    private Integer xlabel;
    private Integer ylabel;

    private Integer zone;
    private Integer occupied;

    public Integer getParkId() {
        return parkId;
    }
    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }
    public Integer getXlabel() {
        return xlabel;
    }
    public void setXlabel(Integer xlabel) {
        this.xlabel = xlabel;
    }
    public Integer getYlabel() {
        return ylabel;
    }
    public void setYlabel(Integer ylabel) {
        this.ylabel = ylabel;
    }
    public Integer getZone() {
        return zone;
    }
    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public Integer getOccupied() {
        return occupied;
    }
    public void setOccupied(Integer occupied) {
        this.occupied = occupied;
    }
}
