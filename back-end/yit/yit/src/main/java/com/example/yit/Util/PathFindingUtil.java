package com.example.yit.Util;

import java.util.LinkedList;
import java.util.Queue;

public class PathFindingUtil {

    // 返回给前端的简化路径坐标
    public class PathCoordinate {
        private int xlabel;
        private int ylabel;

        public PathCoordinate(int xlabel, int ylabel) {
            this.xlabel = xlabel;
            this.ylabel = ylabel;
        }

        public int getXlabel() {
            return xlabel;
        }

        public int getYlabel() {
            return ylabel;
        }
    }

    private int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 四个方向

    /**
     * 根据起始位置、车辆尺寸和停车场地图寻找至最近空停车位的路径。
     *
     * @param map      停车场地图，二维数组，其中包含每个停车位的信息
     * @param startX   起始位置的 X 坐标
     * @param startY   起始位置的 Y 坐标
     * @param carWidth 车辆宽度
     * @param carLength 车辆长度
     * @return 包含路径的坐标列表，如果没有找到路径则返回 null
     */
    public List<PathCoordinate> findPathToNearestEmptyParking(
            ParkLocationEntity[][] map, int startX, int startY, int carWidth, int carLength) {

        // 先遍历整个地图，将停车位及其区域标记为不可通行
        boolean[][] accessibleMap = new boolean[map.length][map[0].length];
        markAccessibleAreas(map, accessibleMap, carWidth, carLength);

        // 队列中的元素需要包括当前的位置坐标和路径
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        // 初始点
        queue.add(new Node(startX, startY, new ArrayList<>()));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int x = current.getX();
            int y = current.getY();
            List<PathCoordinate> currentPath = current.getPath();

            // 检查当前点是否是空停车位
            if (canParkHere(map, x, y)) {
                currentPath.add(new PathCoordinate(x, y));  // 添加最终停车位
                return currentPath;  // 返回路径
            }

            // 向四个方向扩展
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (isValidPosition(accessibleMap, newX, newY, visited)) {
                    visited[newX][newY] = true;
                    List<PathCoordinate> newPath = new ArrayList<>(currentPath);
                    newPath.add(new PathCoordinate(newX, newY));  // 添加路径点
                    queue.add(new Node(newX, newY, newPath));
                }
            }
        }

        // 如果找不到可用的停车位
        return null;
    }

    /**
     * 遍历停车场地图，标记出停车位及其区域为不可通行。
     *
     * @param map           停车场地图，二维数组
     * @param accessibleMap 标记每个位置是否可通行的布尔数组
     * @param carWidth      车辆的宽度
     * @param carLength     车辆的长度
     */
    private void markAccessibleAreas(ParkLocationEntity[][] map, boolean[][] accessibleMap, int carWidth, int carLength) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // 如果这个位置是一个停车位的左上角
                if (map[i][j].getOccupied() == 0) { // 表示是一个空车位
                    // 将这个停车位区域标记为不可通行
                    for (int dx = 0; dx < carLength; dx++) {
                        for (int dy = 0; dy < carWidth; dy++) {
                            if (i + dx < map.length && j + dy < map[0].length) {
                                accessibleMap[i + dx][j + dy] = false; // 标记为不可通行
                            }
                        }
                    }
                } else {
                    accessibleMap[i][j] = true; // 其他地方默认可通行
                }
            }
        }
    }

    /**
     * 检查指定位置是否可以停放车辆。
     *
     * @param map 停车场地图，二维数组
     * @param x   待检查的停车位的 X 坐标
     * @param y   待检查的停车位的 Y 坐标
     * @return 如果可以停放车辆，返回 true；否则返回 false
     */
    private boolean canParkHere(ParkLocationEntity[][] map, int x, int y) {
        ParkLocationEntity spot = map[x][y];
        
        // 检查该位置是否未被占用，并且是一个停车位，且不是道路或障碍物
        return spot.getOccupied() == 0 // 该停车位未被占用
                && !spot.getIsRoad() // 该位置不是道路
                && !spot.getIsObstacle(); // 该位置不是障碍物
    }

    /**
     * 检查指定位置是否有效。
     *
     * @param accessibleMap 可通行区域的布尔数组
     * @param x            待检查的 X 坐标
     * @param y            待检查的 Y 坐标
     * @param visited      访问标记数组
     * @return 如果位置合法且未被访问，返回 true；否则返回 false
     */
    private boolean isValidPosition(boolean[][] accessibleMap, int x, int y, boolean[][] visited) {
        // 确保不超出地图边界，且该点未被访问过，并且是可通行的
        return x >= 0 && y >= 0 && x < accessibleMap.length && y < accessibleMap[0].length
                && !visited[x][y] && accessibleMap[x][y];
    }
}
