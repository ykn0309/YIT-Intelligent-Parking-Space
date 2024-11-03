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
import com.example.yit.Util.*;
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

    public int[][] mapInitial()
    {
        //全赋值1
        int[][] map = new int[29][30]; 
        for(int i=0; i<29;i++)
        {
            for(int j=0; j<30;j++)
            {
                map[i][j]=1;
            }
        }

        
        //赋值空闲车位
        //2
        for(int i=2; i<5;i++)
        {
            for(int j=2; j<14;j++)
            {
                map[i][j]=2;
            }
        }
        for(int i=2; i<5;i++)
        {
            for(int j=16; j<28;j++)
            {
                map[i][j]=2;
            }
        }

        //6
        for(int i=6; i<9;i++)
        {
            for(int j=2; j<14;j++)
            {
                map[i][j]=2;
            }
        }
        for(int i=6; i<9;i++)
        {
            for(int j=16; j<28;j++)
            {
                map[i][j]=2;
            }
        }
        
        //11
        for(int i=11; i<14;i++)
        {
            for(int j=2; j<14;j++)
            {
                map[i][j]=2;
            }
        }
        for(int i=11; i<14;i++)
        {
            for(int j=16; j<28;j++)
            {
                map[i][j]=2;
            }
        }

        //15
        for(int i=15; i<18;i++)
        {
            for(int j=2; j<14;j++)
            {
                map[i][j]=2;
            }
        }
        for(int i=15; i<18;i++)
        {
            for(int j=16; j<28;j++)
            {
                map[i][j]=2;
            }
        }

        //20
        for(int i=20; i<23;i++)
        {
            for(int j=2; j<14;j++)
            {
                map[i][j]=2;
            }
        }
        for(int i=20; i<23;i++)
        {
            for(int j=16; j<28;j++)
            {
                map[i][j]=2;
            }
        }

        //20
        for(int i=24; i<27;i++)
        {
            for(int j=2; j<14;j++)
            {
                map[i][j]=2;
            }
        }
        for(int i=24; i<27;i++)
        {
            for(int j=16; j<28;j++)
            {
                map[i][j]=2;
            }
        }

        map[0][0]=49;

        Iterable<ParkLocationEntity> iterables=parkLocationRepository.findAll();
        for(ParkLocationEntity PLentity : iterables)
        {
           if((PLentity.getOccupied()==1))
           {
            Integer x=PLentity.getXlabel();
            Integer y=PLentity.getYlabel();
            map[x][y]+=4;
           }

        }
        return map;

    }

    public Road getRoad(int[][]map , int x, int y)
    {
        PathFindingUtil pathFindingUtil = new PathFindingUtil();
        pathFindingUtil.findInPath(map, 0, 3);
        List<Integer> res=new ArrayList<>();
        int len=map[0].length;
        for (PathFindingUtil.Coordinate coordinate : pathFindingUtil.path) {
            res.add(coordinate.x*len+coordinate.y+1);
        }
        int destination_x, destination_y;
        destination_x=pathFindingUtil.destination.x;
        destination_y=pathFindingUtil.destination.y;
        ParkLocationEntity temp=parkLocationRepository.findByXAndY(destination_x, destination_y).get();
        Road road=new Road(temp.getParkId(), res);
        return road;
    }


}
