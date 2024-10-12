package com.example.yit.Bean;
import jakarta.persistence.Embeddable;

@Embeddable
public record User(
        int userId,
        String  userName,
        String  password,
        int isAdmin,
        String  carId,
        float   wallet,
        int parkId
) {
    public User(){
        this(0,null,null,0,null,0,0);
    }
    public User(int userId, String userName){
        this(userId,userName,null,0,null,0,0);
    }
}
