# 在Dockerfile目录下执行build命令
docker build -t switchvov/eureka-server-docker:0.0.1 .
# 运行Docker镜像
docker run -d -p 91:91 switchvov/eureka-server-docker:0.0.1

# 登录Docker Hub
docker login
# 推送镜像(先在Docker Hub对应账号下，增加相应仓库)
docker push switchvov/eureka-server-docker:0.0.1
# 搭建私有仓库
docker run -d -p 5000:5000 --restart=always --name registry2 registry:2