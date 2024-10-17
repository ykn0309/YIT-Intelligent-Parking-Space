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
    private Integer parkId;

    private Integer xlabel;
    private Integer ylabel;

    private Integer zone;
    
    private Integer occupied; // 0-not occupied, 1-occupied
    /*if this pos is occupied, only the upper-left flag bit is not 0*/
    private Integer size; // 1-small car(2*3)

    private Boolean isRoad;
    private Boolean isObstacle;
    private Boolean isEntrance;
    private Boolean isExit;

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
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getIsRoad() {
        return isRoad;
    }
    public void setIsRoad(Boolean isRoad) {
        this.isRoad = isRoad;
    }
    public Boolean getIsObstacle() {
        return isObstacle;
    }
    public void setIsObstacle(Boolean isObstacle) {
        this.isObstacle = isObstacle;
    }
    public Boolean getIsEntrance() {
        return isEntrance;
    }
    public void setIsEntrance(Boolean isEntrance) {
        this.isEntrance = isEntrance;
    }
    public Boolean getIsExit() {
        return isExit;
    }
    public void setIsExit(Boolean isExit) {
        this.isExit = isExit;
    }
}
