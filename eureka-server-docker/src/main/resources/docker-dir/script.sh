# 在Dockerfile目录下执行build命令
docker build -t switchvov/eureka-server-docker:0.0.1 .
# 运行Docker镜像
docker run -d -p 91:91 switchvov/eureka-server-docker:0.0.1