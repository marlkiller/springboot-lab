# Continuous integration of the latest version of springboot

## System 

[//]: # (mvn dependency:tree)
- Maven : >= 3.6.3
- JDK : >= 20
- SpringBoot : = 3.1.0
- tomcat : = 10.1.8

## Build 

### Standard jar
```shell
mvn -DskipTests=true package
```
### Native
```shell
# download graalvm : https://www.graalvm.org/downloads/
wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.2/graalvm-ce-java17-darwin-amd64-22.3.2.tar.gz
gu install native-image
```
```
openjdk version "17.0.7" 2023-04-18
OpenJDK Runtime Environment GraalVM CE 22.3.2 (build 17.0.7+7-jvmci-22.3-b18)
OpenJDK 64-Bit Server VM GraalVM CE 22.3.2 (build 17.0.7+7-jvmci-22.3-b18, mixed mode, sharing)
```

pom.xml
```xml
<!--  spring-boot-devtools dependency is not supported -->
<properties>
    <java.version>17</java.version>
</properties>
```
```shell
# https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#native-image.developing-your-first-application.native-build-tools.mavenhttps://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#native-image.developing-your-first-application.native-build-tools.maven
mvn -Pnative native:compile
```

### Docker image
```shell
# https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#container-images
docker build -t marlkiller/springboot:latest --build-arg JAR_FILE=target/springboot-lab-0.0.1-SNAPSHOT.jar .
# --rm 指定容器停止后自动删除容器(不支持以docker run -d启动的容器) 
docker run --rm --name springboot -p 8080:8080 marlkiller/springboot:latest
```
