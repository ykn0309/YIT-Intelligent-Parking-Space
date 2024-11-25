# 使用官方 Python 镜像作为基础镜像
FROM python:3.9

# 设置工作目录
WORKDIR /app/python-server

# 将项目文件复制到容器中
COPY . .

# 安装依赖
RUN pip install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cpu
RUN pip install --no-cache-dir -r requirements.txt

# 设置容器启动时运行的命令
CMD ["python", "server.py"]
