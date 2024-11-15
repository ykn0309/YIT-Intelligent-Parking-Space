# YIT-Intelligent-Parking-Space
An intelligent parking space

# 运行前端
```
    npm install
    npm run dev
```

# 运行python-server

1. 切换到python-server目录。

    ``` bash
    cd back-end/python-server
    ```

2. 安装依赖

    直接安装torch会自动下载GPU版本，下载和安装速度很慢，建议去[torch官网](https://pytorch.org/get-started/locally/)选择自己的平台、版本获取CPU版的下载命令。
    
    Linux：

    ``` bash
    pip3 install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cpu
    ```

    Windows:

    ```
    pip3 install torch torchvision torchaudio
    ```
    其他依赖：

    ``` 
    pip install -r requirements.txt
    ```

3. 运行

    ``` bash
    python3 server.py
    ```

4. 目前部分表需要手动创建，还有的表需要手动导入数据

    ```sql
    -- 插入一些用户到users，表示已注册并登记车牌号的用户
    insert into users (user_id, car_id, is_admin, wallet) values
    (1, '京EA5331', 1, 10000.0);
    insert into users (user_id, car_id, is_admin, wallet) values
    (2, '沪AN9241', 1, 10000.0);

    -- 创建dynamic_map表，后面会让python-server启动时自动创建
    create table dynamic_map (
        id bigint primary key,
        data blob
    );

    -- 导入parklocations

    -- 运行mysql。linux需要加参数，windows不清楚
    mysql -u root -p --local-infile=1

    -- 设置权限
    set global local_infile=on;

    -- 导入数据，自己修改文件路径
    load data local infile 'map/ParkLocationEntity_a.csv' into table parklocations fields terminated by ',' ignore 1 lines (park_id, xlabel, ylabel, zone, occupied);
    ```