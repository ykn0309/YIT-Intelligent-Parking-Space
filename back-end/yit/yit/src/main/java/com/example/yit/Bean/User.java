package com.example.yit.Bean;
import jakarta.persistence.Embeddable;

/* 2024/10/17 gpt:
   @Embeddable 注解表示这个类可以嵌入到其他实体类中。不过，record 不能与 @Embeddable 一起使用。
   @Embeddable 适用于传统的POJO实体类，而record 不符合JPA要求的无参构造函数等规则。
*/
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
