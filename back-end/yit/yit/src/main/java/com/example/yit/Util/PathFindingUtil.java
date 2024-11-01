/**
 * PathFindingUtil.java
 * 
 * 创建者: FJL
 * 创建日期: 2024-11-01
 * 版本: 1.0
 * 
 * 功能描述:
 * 本类实现了基于 A* 算法的路径查找功能。它能够在给定的地图中找到从起始位置到最近车位或出口的路径。
 * 
 * 使用方法：
 * 1. 创建 PathFindingUtil 的实例。它的public成员有终点坐标destination和路径列表path。
 * 2. 使用 findInPath(int[][] map, int startX, int startY) 方法查找最近的车位。
 * 3. 使用 findOutPath(int[][] map, int startX, int startY) 方法查找最近的出口。
 * 4. 使用 printParkingSpots() 和 printExits() 方法打印当前车位和出口信息。（调试用）
 */

package com.example.yit.Util;

import java.util.*;

public class PathFindingUtil {

    public class Coordinate {
        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Coordinate)) return false;
            Coordinate other = (Coordinate) obj;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public Coordinate destination; // 目的地坐标
    public List<Coordinate> path; // 路径坐标列表

    private final int[][] directions = {
        {1, 0},  // 下
        {-1, 0}, // 上
        {0, 1},  // 右
        {0, -1}  // 左
    };

    // 节点类（A*算法使用）
    private static class Node {
        public Coordinate coordinate;
        public Node parent;
        public int g; // 实际成本
        public int h; // 预估成本

        public Node(Coordinate coordinate, Node parent, int g, int h) {
            this.coordinate = coordinate;
            this.parent = parent;
            this.g = g;
            this.h = h;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Node)) return false;
            Node other = (Node) obj;
            return this.coordinate.equals(other.coordinate);
        }

        @Override
        public int hashCode() {
            return coordinate.hashCode();
        }
    }

    /**
    map数组的值规定如下：
    最后1位：是否可以通行：1-是；0-否
    倒数第2位：是否是车位：1-是；0-否
    倒数第3位：是否车位被占用了：1-是；0-否
    倒数第4位：是否是车位的左上角：1-是；0-否
    倒数第5位：是否是出口：1-是；0-否
    倒数第6位：是否是入口：1-是；0-否
     */

    // 存储车位和出口的坐标
    private Set<Coordinate> parkingSpots = new HashSet<>();
    private Set<Coordinate> exits = new HashSet<>();

    /**
     * 初始化地图，收集车位和出口的坐标。
     *
     * @param map 地图二维数组
     */
    public void initMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // 收集车位
                if ((map[i][j] & 0b00001000) != 0 && (map[i][j] & 0b00000100) == 0) {
                    parkingSpots.add(new Coordinate(i, j));
                }
                // 收集出口
                if ((map[i][j] & 0b00100000) != 0) {
                    exits.add(new Coordinate(i, j));
                }
            }
        }
    }

    /**
     * 查找离给定坐标最近的车位。
     *
     * @param map     地图二维数组
     * @param startX  起始位置的 X 坐标
     * @param startY  起始位置的 Y 坐标
     */
    public void findInPath(int[][] map, int startX, int startY) {
        initMap(map); // 初始化地图
        findPathAStar(map, new Coordinate(startX, startY), true); // 找车位
    }

    /**
     * 查找离给定坐标最近的出口。
     *
     * @param map     地图二维数组
     * @param startX  起始位置的 X 坐标
     * @param startY  起始位置的 Y 坐标
     */
    public void findOutPath(int[][] map, int startX, int startY) {
        initMap(map); // 初始化地图
        findPathAStar(map, new Coordinate(startX, startY), false); // 找出口
    }

    /**
     * 使用 A* 算法查找路径。
     *
     * @param map             地图
     * @param start           起始位置坐标
     * @param findParking     是否查找车位
     */
    private void findPathAStar(int[][] map, Coordinate start, boolean findParking) {
        // 确定目标坐标
        Coordinate target = determineTarget(findParking, start);
        if (target == null) {
            System.out.println("未选择有效目标，路径搜索失败");
            return;
        }

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.g + node.h));
        Set<Coordinate> closedSet = new HashSet<>();

        Node startNode = new Node(start, null, 0, heuristic(start, target));
        openSet.add(startNode);
        System.out.println("起始节点: " + start.x + ", " + start.y);
        System.out.println("目标节点: " + target.x + ", " + target.y);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            closedSet.add(current.coordinate); // 将当前节点添加到 closedSet
            // System.out.println("当前节点: " + current.coordinate.x + ", " + current.coordinate.y);

            if (current.coordinate.equals(target)) {
                // System.out.println("目标节点找到: " + current.coordinate.x + ", " + current.coordinate.y);
                reconstructPath(current);
                return;
            }

            for (int[] direction : directions) {
                int newX = current.coordinate.x + direction[0];
                int newY = current.coordinate.y + direction[1];
                // System.out.println("方向：" + direction[0] + ", " + direction[1]);
            
                if (newX == target.x && newY == target.y) { // 即将找到目标
                    Coordinate newCoordinate = new Coordinate(newX, newY);
                    int newG = current.g + 1;
                    int newH = 0;
                    Node neighborNode = new Node(newCoordinate, current, newG, newH);
                    openSet.add(neighborNode);
                    continue;
                }

                if (isValidPosition(map, newX, newY)) {
                    Coordinate newCoordinate = new Coordinate(newX, newY);
                    if (closedSet.contains(newCoordinate)) {
                        // System.out.println("邻居节点在closedset: " + newCoordinate.x + ", " + newCoordinate.y);
                        continue; // 如果已经处理过，跳过
                    }
            
                    int newG = current.g + 1; // 每一步成本为1
                    int newH = heuristic(newCoordinate, target);
                    Node neighborNode = new Node(newCoordinate, current, newG, newH);
            
                    // 检查是否已经在 openSet 中
                    boolean inOpenSet = false;
                    for (Node node : openSet) {
                        if (node.coordinate.equals(newCoordinate) && newG >= node.g) {
                            inOpenSet = true; // 找到在 openSet 中，且代价更高
                            // System.out.println("邻居节点在openset: " + newCoordinate.x + ", " + newCoordinate.y);
                            break;
                        }
                    }
            
                    // 如果不在 openSet 中或者找到更低的 g 值
                    if (!inOpenSet) {
                        openSet.add(neighborNode);
                        // System.out.println("加入邻居节点: " + newCoordinate.x + ", " + newCoordinate.y + 
                        //     " | G: " + newG + " | H: " + newH);
                    }
                }
            }
        }

        // 如果没有找到路径，清空路径
        System.out.println("没有找到路径");
        path = new ArrayList<>();
    }

    /**
     * 估算函数（启发式函数），使用2倍的曼哈顿距离 (后续需要调整)
     *
     * @param current 当前坐标
     * @param target  目标坐标
     * @return 从当前坐标到目标坐标的估算距离
     */
    private int heuristic(Coordinate current, Coordinate target) {
        return 2 * man(current, target);
    }

    /**
     * 曼哈顿距离计算
     *
     * @param current 当前坐标
     * @param target  目标坐标
     * @return 从当前坐标到目标坐标的曼哈顿距离
     */
    private int man(Coordinate current, Coordinate target) {
        return Math.abs(current.x - target.x) + Math.abs(current.y - target.y);
    }

    /**
     * 确定目标坐标
     *
     * @param findParking     是否查找车位
     * @param start 起始位置坐标
     * @return 确定的目标坐标，或 null 如果没有有效目标
     */
    private Coordinate determineTarget(boolean findParking, Coordinate start) {
        // 根据 findParking 选择目标
        int closestDistance = Integer.MAX_VALUE;
        Coordinate closestTarget = null;

        // 遍历停车位
        if (findParking) {
            for (Coordinate parkingSpot : parkingSpots) {
                int distance = man(start, parkingSpot);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestTarget = parkingSpot;
                }
            }
        }

        // 遍历出口
        if (!findParking) {
            for (Coordinate exit : exits) {
                int distance = man(start, exit);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestTarget = exit;
                }
            }
        }

        return closestTarget; // 返回最近的目标，或 null 如果没有有效目标
    }

    /**
     * 重建路径，从目标节点回溯到起始节点。
     *
     * @param current 当前节点，也就是找到的目标节点
     */
    private void reconstructPath(Node current) {
        path = new ArrayList<>();
        while (current != null) {
            path.add(current.coordinate);
            current = current.parent;
        }
        Collections.reverse(path); // 反转路径
    }

    /**
     * 检查位置是否有效。
     *
     * @param map     地图二维数组
     * @param x       待检查的 X 坐标
     * @param y       待检查的 Y 坐标
     * @return 如果位置有效，返回 true；否则返回 false
     */
    private boolean isValidPosition(int[][] map, int x, int y) {
        // 确保不超出地图边界，且该点可以通行
        return x >= 0 && y >= 0 
               && x < map.length && y < map[0].length 
               && (map[x][y] & 0b00000001) != 0; // 最后一位是1表示可通行
    }

    // 调试用打印函数
    public void printParkingSpots() {
        System.out.print("车位: ");
        if (parkingSpots.isEmpty()) {
            System.out.println("没有车位");
        } else {
            for (Coordinate spot : parkingSpots) {
                System.out.print("(" + spot.x + ", " + spot.y + ") ");
            }
            System.out.println();
        }
    }

    public void printExits() {
        System.out.print("出口: ");
        if (exits.isEmpty()) {
            System.out.println("没有出口");
        } else {
            for (Coordinate exit : exits) {
                System.out.print("(" + exit.x + ", " + exit.y + ") ");
            }
            System.out.println();
        }
    }
}
