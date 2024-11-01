package com.example.yit.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parkrecords")
public class ParkRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer recordId;

    private Integer parkId;
    private Integer userId;
    private String carId;
    private String startTime;
    private String endTime;

        public Integer getRecordId() {
            return recordId;
        }
    
        public void setRecordId(Integer recordId) {
            this.recordId = recordId;
        }
    
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
    
        public String getStartTime() {
            return startTime;
        }
    
        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }
    
        public String getEndTime() {
            return endTime;
        }
    
        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
}
