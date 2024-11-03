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
    @Autowired
    ParkRecordRepository parkRecordRepository;

    //获取所有车位信息
    public List<Park> getCarsMap()
    {
        List<Park> result=new ArrayList<>();
        Iterable<ParkLocationEntity> iterables=parkLocationRepository.findAll();
        for (ParkLocationEntity PLentity : iterables) 
        {
            Integer isOccupied=PLentity.getOccupied();
            if(isOccupied==1)
            {
                OccupiedParkEntity OPentity=occupiedParkRepository.findById(PLentity.getParkId()).get();
                Park ypark=new Park(OPentity.getParkId(),OPentity.getUserId(),OPentity.getCarId(),OPentity.getStartTime(),true);
                result.add(ypark);
            }
            if(isOccupied==0)
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

    //获取已经停的车位信息
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

    //车进入停车场
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

    //车离开停车场
    public Integer deleteCar(int parkId)
    {
        occupiedParkRepository.deleteById(parkId);
        ParkLocationEntity temp=parkLocationRepository.findById(parkId).get();
        temp.setYlabel(0);
        temp.setXlabel(0);
        temp.setOccupied(0);
        parkLocationRepository.save(temp);
        return parkId;
    }

    //记录停车信息
    public Integer addRecord(Integer parkId, String endTime)
    {
        OccupiedParkEntity temp=occupiedParkRepository.findById(parkId).get();
        ParkRecordEntity parkRecordEntity=new ParkRecordEntity();
        parkRecordEntity.setCarId(temp.getCarId());
        parkRecordEntity.setStartTime(temp.getStartTime());
        parkRecordEntity.setParkId(parkId);
        parkRecordEntity.setUserId(temp.getUserId());
        parkRecordEntity.setEndTime(endTime);
        parkRecordRepository.save(parkRecordEntity);
        return 1;
    }  

    //获取所有停车记录
    public List<ParkRecord> getAllRecord()
    {
        List<ParkRecord> result=new ArrayList<>();
        Iterable<ParkRecordEntity> iterables=parkRecordRepository.findAll();
        for(ParkRecordEntity PRentity : iterables)
        {
            ParkRecord parkRecord=new ParkRecord(PRentity.getParkId(),PRentity.getUserId(),
                                                PRentity.getCarId(),PRentity.getStartTime(),PRentity.getEndTime());
            result.add(parkRecord);
        }
        result.sort(
                Comparator.comparing(ParkRecord::parkId)
                        .thenComparing(ParkRecord::startTime)
        );
        return result;
    }

}
