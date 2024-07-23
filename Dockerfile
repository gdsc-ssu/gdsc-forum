# docker build -t gdsc-forum-application .
FROM amazoncorretto:17.0.10-al2023 AS base
WORKDIR /app
COPY /build/libs/gdsc-forum-0.0.1-SNAPSHOT.jar /app.jar
RUN java -Djarmode=layertools -jar /app.jar extract

FROM amazoncorretto:17.0.10-al2023
EXPOSE 8080
COPY --from=base /app/dependencies/ ./
COPY --from=base /app/spring-boot-loader/ ./
COPY --from=base /app/snapshot-dependencies/ ./
COPY --from=base /app/application/ ./
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8
ENV TZ Asia/Seoul

ENTRYPOINT ["java", "-Dspring.profiles.active=local", "org.springframework.boot.loader.launch.JarLauncher"]