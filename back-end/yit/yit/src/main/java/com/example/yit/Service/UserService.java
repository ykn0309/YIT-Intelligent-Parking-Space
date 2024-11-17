package com.example.yit.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.yit.Entity.UserEntity;
import com.example.yit.Bean.User;
import com.example.yit.Repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        for(var usr : userRepository.findAll()){
            users.add(new User(usr.getUserId(), usr.getUserName()));
        }
        return users;
    }

    public UserEntity getUserById(String username){
        Optional<UserEntity> p = userRepository.findByUserName(username);
        if(p.isPresent()){
            System.out.println("获取密码：");
            return p.get();}
        else {
            return null;
        }
    }

    public int addNewUser(UserEntity userEntity){
        String username= userEntity.getUserName();
        Optional <UserEntity> p = userRepository.findByUserName(username);
        if(p.isPresent()){
            return 0;
        }
        else{
            System.out.println("注册用户：");
            userRepository.save(userEntity);
            return 1;
        }
    }

    ///  gfx's functions

    public User userInfo(int userId)
    {
        UserEntity temp=userRepository.findById(userId).get();
        User result=new User(0,temp.getUserName(),null,0,temp.getCarId(),0,0);
        return result;
    }

    public float wallet(int userId)
    {
        UserEntity temp=userRepository.findById(userId).get();
        float result=temp.getWallet();
        return result;
    }

    public float recharge(int userId,float wallet)
    {
        UserEntity temp=userRepository.findById(userId).get();
        float status=temp.getWallet()+wallet;
        System.out.println("recharge: "+status);
        temp.setWallet(status);
        userRepository.save(temp);
        return status;
    }

    public int updateUserInfo(int userId, String userName, String carId)
    {
        UserEntity temp=userRepository.findById(userId).get();
        temp.setCarId(carId);
        temp.setUserName(userName);
        System.out.println("update id:"+userId+" username:"+userName+" carId:"+carId);
        userRepository.save(temp);
        return userId;
    }

    public String vehicle(int userId)
    {
        UserEntity temp=userRepository.findById(userId).get();
        String result=temp.getCarId();
        return result;
    }

    
}
