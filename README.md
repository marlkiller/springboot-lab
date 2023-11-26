# Continuous integration of the latest version of springboot

## System 

[//]: # (mvn dependency:tree)
- Maven : >= 3.6.3
- JDK : >= 21
- SpringBoot : = 3.2.0
- tomcat : = 10.1.8

## Build 

### Standard jar
```shell
mvn -DskipTests=true package
```
### Native
```shell
# download graalvm : https://www.graalvm.org/downloads/
## graalvm 21 已经集成 native-image, 并且废弃了 gu 命令,旧版本需要下面命令安装 native-image  
## gu install native-image
```
```
java version "21.0.1" 2023-10-17
Java(TM) SE Runtime Environment Oracle GraalVM 21.0.1+12.1 (build 21.0.1+12-jvmci-23.1-b19)
Java HotSpot(TM) 64-Bit Server VM Oracle GraalVM 21.0.1+12.1 (build 21.0.1+12-jvmci-23.1-b19, mixed mode, sharing)
```

pom.xml
```xml
<!--  spring-boot-devtools dependency is not supported -->
<properties>
    <java.version>21</java.version>
</properties>
```
```shell
# https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#native-image.developing-your-first-application.native-build-tools.mavenhttps://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#native-image.developing-your-first-application.native-build-tools.maven
mvn -Pnative -DskipTests clean native:compile
```

### Docker image
```shell
# https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#container-images
docker build -t marlkiller/springboot:latest --build-arg JAR_FILE=target/springboot-lab-0.0.1-SNAPSHOT.jar .
# --rm 指定容器停止后自动删除容器(不支持以docker run -d启动的容器) 
docker run --rm --name springboot -p 8080:8080 marlkiller/springboot:latest
```
