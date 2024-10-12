package com.example.yit.Bean;

public record Park(
        int parkId,
        int userId,

        String carId,

        String startDate,

        boolean occupied

        //siuuuuuuuuu
        //cristiano
) {
    public Park(){this(0,0,null,null,false);
    }
}
