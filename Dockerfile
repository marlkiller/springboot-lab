FROM eclipse-temurin:21-jre as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
# 通过 jarmode 系统属性来提取 jar 包中的层。
RUN java -Djarmode=layertools -jar application.jar extract  
#ENTRYPOINT ["java","${JAVA_OPTS}","-jar","application.jar"]


FROM eclipse-temurin:21-jre
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
