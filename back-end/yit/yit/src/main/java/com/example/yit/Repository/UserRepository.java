package com.example.yit.Repository;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

import com.example.yit.Entity.UserEntity;
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserName(String username);
}
