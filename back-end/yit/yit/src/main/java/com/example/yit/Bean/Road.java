package com.example.yit.Bean;
import java.util.List;
public record Road (
    int parkId,
    List<Integer> road
){
public Road(){this(0,null);}
}
