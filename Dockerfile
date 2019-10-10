
FROM maven:3.6.2-jdk-11-slim AS BUILDER
COPY . .
RUN mvn versions:set -DnewVersion=1.0
RUN mvn package

#Made to run in Openshift
FROM openjdk:11.0.3-jdk-slim-stretch
MAINTAINER zero@dividebyzero.cc

RUN mkdir -p /home/app
WORKDIR /home/app
COPY --from=BUILDER /target/r2gapi-1.0.jar .

ENTRYPOINT java -XX:+PrintFlagsFinal -Djava.security.egd=file:/dev/./urandom $JAVA_OPTIONS -jar app.jar
