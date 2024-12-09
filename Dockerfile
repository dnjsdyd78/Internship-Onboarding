FROM openjdk:17-jdk-slim

# 애플리케이션 JAR 파일 복사
COPY build/libs/Spring-Security-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/application.properties application.properties

# Spring Boot가 루트 경로에 있는 설정 파일을 참조하도록 설정
ENV SPRING_CONFIG_LOCATION=file:/application.properties

# wait-for-it.sh 스크립트를 컨테이너로 복사
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# wait-for-it.sh를 통해 db 서비스가 시작될 때까지 대기한 후 애플리케이션 시작
CMD ["/wait-for-it.sh", "${DB_ENDPOINT}:3306", "--", "java", "-jar", "app.jar"]
