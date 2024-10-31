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