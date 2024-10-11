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
            userRepository.save(userEntity);
            return 1;
        }
    }
}
