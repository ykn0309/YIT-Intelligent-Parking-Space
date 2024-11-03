package com.example.yit.Repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
import com.example.yit.Entity.ParkRecordEntity;


public interface ParkRecordRepository extends CrudRepository<ParkRecordEntity, Integer>{
    Optional<ParkRecordEntity> findByParkId(String parkId);
    
}
