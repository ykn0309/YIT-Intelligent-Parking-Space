# 使用 Nginx 作为 web 服务器来服务前端资源
FROM nginx:alpine

# 将构建出来的静态文件复制到 Nginx 服务器中
COPY dist/ /usr/share/nginx/html

# 配置 Nginx 启动服务
CMD ["nginx", "-g", "daemon off;"]
