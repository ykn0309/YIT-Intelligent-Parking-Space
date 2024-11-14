package com.example.yit.Util;

import java.util.*;

import com.example.yit.Service.*;

public class TestUtil {

    // 10x10的地图数组
    // 1: 可通行, 2: 车位, 4: 出口, 6: 入口
    public static final int[][] MAP = {
        {0b00000001, 0b00000000, 0b00000001, 0b00110001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001},
        {0b00000001, 0b00001110, 0b00000110, 0b00001110, 0b00000110, 0b00001010, 0b00000010, 0b00001110, 0b00000110, 0b00000001},
        {0b00000001, 0b00000110, 0b00000110, 0b00000110, 0b00000110, 0b00000010, 0b00000010, 0b00000110, 0b00000110, 0b00000001},
        {0b00000001, 0b00000110, 0b00000110, 0b00000110, 0b00000110, 0b00000010, 0b00000010, 0b00000110, 0b00000110, 0b00000001},
        {0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001},
        {0b00000001, 0b00001110, 0b00000110, 0b00001010, 0b00000010, 0b00001110, 0b00000110, 0b00001110, 0b00000110, 0b00000001},
        {0b00000001, 0b00000110, 0b00000110, 0b00000010, 0b00000010, 0b00000110, 0b00000110, 0b00000110, 0b00000110, 0b00000000},
        {0b00000001, 0b00000110, 0b00000110, 0b00000010, 0b00000010, 0b00000110, 0b00000110, 0b00000110, 0b00000110, 0b00000001},
        {0b00000001, 0b00000000, 0b00000000, 0b00000001, 0b00000000, 0b00000000, 0b00000000, 0b00000001, 0b00000001, 0b00000001},
        {0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00000001, 0b00110001}
    };

    public static void main(String[] args) {
        ParkService parkService = new ParkService();
        parkService.mapInitial();
        
        parkService.getRoad(0, 0);
        parkService.getRoad(0, 0);
        parkService.getRoad(0, 0);
        // PathFindingUtil pathFindingUtil = new PathFindingUtil();
    
        // // 查找离(0, 3)最近的车位
        // pathFindingUtil.findInPath(MAP, 0, 3);
        // System.out.print("车位路径: ");
        // printPath(pathFindingUtil.path);
        
        // // 查找离(9, 9)最近的车位
        // pathFindingUtil.findInPath(MAP, 9, 9);
        // System.out.print("车位路径: ");
        // printPath(pathFindingUtil.path);
    
        // // 查找离(29, 29)最近的出口
        // pathFindingUtil.findOutPath(MAP, 29, 29);
        // System.out.print("出口路径: ");
        // printPath(pathFindingUtil.path);

        // // 输出车位和出口
        // pathFindingUtil.printParkingSpots();
        // pathFindingUtil.printExits();
    }
    
    // 辅助方法，用于打印路径
    private static void printPath(List<PathFindingUtil.Coordinate> path) {
        if (path.isEmpty()) {
            System.out.println("没有找到路径");
        } else {
            for (PathFindingUtil.Coordinate coordinate : path) {
                System.out.print("(" + coordinate.x + ", " + coordinate.y + ") ");
            }
            System.out.println();
        }
    }
}