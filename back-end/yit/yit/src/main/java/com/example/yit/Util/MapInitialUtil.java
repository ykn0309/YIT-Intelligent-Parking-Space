package com.example.yit.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapInitialUtil {

    /**
     * 从 CSV 文件读取地图数据，生成一个 int[][] 数组。
     * 假设每个元素之间用逗号分隔，并且所有值都为有效整数。
     *
     * @param filePath CSV 文件的路径
     * @return 从文件读取并生成的 int[][] 地图数组
     * @throws IOException 如果文件读取过程中出现错误
     */
    public static int[][] loadMapFromCSV(String filePath) throws IOException {
        List<int[]> mapList = new ArrayList<>(); // 用于动态存储每一行的数组

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");  // 以逗号分割每一行的数据
                int[] row = new int[values.length];

                for (int i = 0; i < values.length; i++) {
                    row[i] = Integer.parseInt(values[i].trim()); // 去除空格并转换为整数
                }
                mapList.add(row); // 将每一行的数据存入 mapList
            }
        }

        // 将 List<int[]> 转换为 int[][] 数组
        int[][] map = new int[mapList.size()][];
        for (int i = 0; i < mapList.size(); i++) {
            map[i] = mapList.get(i);
        }

        return map;
    }
}
