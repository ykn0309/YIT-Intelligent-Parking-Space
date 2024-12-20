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
    @Column(name = "parkId")
    private Integer parkId;
    @Column(name = "xlabel")
    private Integer xlabel;
    @Column(name = "ylabel")
    private Integer ylabel;
    @Column(name = "zone")
    private Integer zone;
    @Column(name = "occupied")
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
