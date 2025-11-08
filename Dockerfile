FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

COPY presentation ./presentation
COPY domain ./domain
COPY infrastructure ./infrastructure
COPY application ./application

WORKDIR /app/presentation

RUN ../gradlew clean bootJar -x test

FROM amazoncorretto:21

WORKDIR /app

COPY --from=builder /app/presentation/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
