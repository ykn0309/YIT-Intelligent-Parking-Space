package com.example.yit.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.yit.Entity.*;
import com.example.yit.Bean.*;
import com.example.yit.Repository.*;
@Service
@Transactional
public class ParkService {
    @Autowired
    OccupiedParkRepository occupiedParkRepository;
    @Autowired
    ParkLocationRepository parkLocationRepository;

    public List<Park> getCarsMap()
    {
        List<Park> result=new ArrayList<>();
        Iterable<ParkLocationEntity> iterables=parkLocationRepository.findAll();
        for (ParkLocationEntity PLentity : iterables) {
            Integer isOccupied=PLentity.getOccupied();
            if(isOccupied==1)
            {
                OccupiedParkEntity OPentity=occupiedParkRepository.findById(PLentity.getParkId()).get();
                Park ypark=new Park(OPentity.getParkId(),OPentity.getUserId(),OPentity.getCarId(),OPentity.getStartTime(),true);
                result.add(ypark);
            }
            else
            {
                Park npark=new Park(PLentity.getParkId(),-1,null,null,false);
                result.add(npark);
            }
        }
        result.sort(
                Comparator.comparing(Park::parkId)
                        .thenComparing(Park::startTime)
        );
        return result;
    }

    public List<Park> getAllCars()
    {
        List<Park> result=new ArrayList<>();
        Iterable<OccupiedParkEntity> iterables=occupiedParkRepository.findAll();
        for(OccupiedParkEntity OPentity : iterables)
        {
            Park park=new Park(OPentity.getParkId(),OPentity.getUserId(),OPentity.getCarId(),OPentity.getStartTime(),true);
            result.add(park);
        }
        result.sort(
                Comparator.comparing(Park::parkId)
                        .thenComparing(Park::startTime)
        );
        return result;
    }

    public Integer addCar(Park park)
    {
        OccupiedParkEntity OPentity=new OccupiedParkEntity();
        ParkLocationEntity PLentity=new ParkLocationEntity();

        OPentity.setCarId(park.carId());
        OPentity.setParkId(park.parkId());
        OPentity.setOccupied(1);
        OPentity.setUserId(park.userId());
        OPentity.setStartTime(park.startTime());
        occupiedParkRepository.save(OPentity);

        PLentity.setOccupied(1);
        PLentity.setParkId(park.parkId());
        PLentity.setXlabel(0);
        PLentity.setYlabel(0);
        PLentity.setZone(0);
        parkLocationRepository.save(PLentity);

        return park.parkId();
    }

    public Integer deleteCar(Park park)
    {
        occupiedParkRepository.deleteById(park.parkId());
        parkLocationRepository.deleteById(park.parkId());
        return park.parkId();
    }

}
