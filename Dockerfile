FROM maven:3.8.3 as builder

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn package

FROM openjdk:17.0-jdk-slim
ARG JAR_FILE=tictactoe-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY --from=builder /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","tictactoe-0.0.1-SNAPSHOT.jar"]