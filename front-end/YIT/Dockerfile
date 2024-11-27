# 使用官方 Nginx 镜像作为基础镜像
FROM nginx:alpine

# 将自定义的 nginx.conf 配置文件复制到镜像中的 Nginx 配置目录
COPY nginx.conf /etc/nginx/nginx.conf

# 将 Vue.js 构建后的静态文件复制到 Nginx 的默认静态文件目录
COPY dist/ /usr/share/nginx/html/

# 暴露端口 80
EXPOSE 80

# 启动 Nginx 服务
CMD ["nginx", "-g", "daemon off;"]

