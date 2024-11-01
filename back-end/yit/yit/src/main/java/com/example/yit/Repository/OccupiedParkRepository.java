package com.example.yit.Repository;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import com.example.yit.Entity.OccupiedParkEntity;

public interface OccupiedParkRepository extends CrudRepository<OccupiedParkEntity, Integer>{
    Optional<OccupiedParkEntity> findByCarId(String carId);

}
