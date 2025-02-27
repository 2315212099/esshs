# 使用 JDK 17 作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制 Maven 构建的 jar 文件到容器中
COPY target/*.jar app.jar

# 暴露端口
EXPOSE 8080

# 设置默认配置文件为 prod
ENV SPRING_PROFILES_ACTIVE=prod

# 设置容器启动时运行的命令
ENTRYPOINT ["java", "-jar", "app.jar"]
