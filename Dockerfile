FROM adoptopenjdk:11-jdk-hotspot as builder
WORKDIR application
COPY gradlew gradlew
COPY settings.gradle.kts settings.gradle.kts
COPY build.gradle.kts build.gradle.kts
COPY gradle gradle
COPY src src
RUN ./gradlew --no-daemon bootJar

FROM adoptopenjdk:11-jre-hotspot
WORKDIR application
ARG LIBS=/application/build/libs
COPY --from=builder ${LIBS}/eng-mgr-take-home-challenge-0.0.1-SNAPSHOT.jar api.jar
ENTRYPOINT ["java", "-noverify", "-jar", "api.jar"]