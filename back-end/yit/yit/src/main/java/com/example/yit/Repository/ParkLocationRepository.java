package com.example.yit.Repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import com.example.yit.Entity.ParkLocationEntity;
public interface ParkLocationRepository extends CrudRepository<ParkLocationEntity, Integer>{
    //List<ParkLocationEntity> findBySomeCriteria();
}
