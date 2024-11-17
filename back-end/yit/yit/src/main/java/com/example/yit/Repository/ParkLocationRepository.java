package com.example.yit.Repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
import com.example.yit.Entity.ParkLocationEntity;
import com.example.yit.Entity.UserEntity;

import org.springframework.data.jpa.repository.Query;  
import org.springframework.data.repository.query.Param;  

public interface ParkLocationRepository extends CrudRepository<ParkLocationEntity, Integer>{
    //List<ParkLocationEntity> findBySomeCriteria();
    @Query("SELECT p FROM ParkLocationEntity p WHERE p.xlabel = :x AND p.ylabel = :y")  
    Optional<ParkLocationEntity> findByXAndY(@Param("x") int x, @Param("y") int y);
}

