package com.example.yit.Bean;

public record ParkRecord(
    int  parkId,
    int userId,
    String carId,
    String startTime,
    String endTime
) {
    public ParkRecord(){this(0,0,null,null,null);
    }
}
