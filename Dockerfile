# syntax=docker/dockerfile:1

FROM openjdk:17-jdk-alpine

WORKDIR /usr/src/aoide-boot
EXPOSE 8081

CMD [ "java", "-version" ]